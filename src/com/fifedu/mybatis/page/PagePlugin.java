package com.fifedu.mybatis.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.ctakit.common.util.page.imp.Page;
import com.fifedu.mybatis.utils.ReflectHelper;

@Intercepts({ @org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.statement.StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {

	private static String dialect = "";
	private static String pageSqlId = "";

	public Object intercept(Invocation ivk) throws Throwable {

		if (ivk.getTarget() instanceof RoutingStatementHandler) {

			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
					.getTarget();
			
		 
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
					.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper
					.getValueByFieldName(delegate, "mappedStatement");

			Page page;
			if ((page = (Page) Page.threadLocal.get()) == null) {
				page = new Page();
				Page.threadLocal.set(page);
			}

			if (mappedStatement.getId().matches(".*(" + pageSqlId + ")$")
					&& (page.getPageSize() > 0)) {
				MappedStatement selfStatement =null;
				try {
					  selfStatement=	mappedStatement.getConfiguration().getMappedStatement(mappedStatement.getId()+"Count");
				} catch ( Exception e) {
				 
				}
				
				String selfCountSql =null;
				if(selfStatement!=null){
					  selfCountSql =mappedStatement.getConfiguration().getMappedStatement(mappedStatement.getId()+"Count").getBoundSql(null).getSql();	
				}
				
				
				
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();
				if (parameterObject == null) {
					parameterObject = "";
				}
				Connection connection = (Connection) ivk.getArgs()[0];
				String sql = boundSql.getSql();
				
				String countSql = "select count(0) from (" + sql + ") myCount ";
				if(selfCountSql!=null){
					countSql=selfCountSql;
				}
				// System.out.println("总数sql 语句:"+countSql);
				PreparedStatement countStmt = connection
						.prepareStatement(countSql);
				BoundSql countBS = new BoundSql(
						mappedStatement.getConfiguration(), countSql,
						boundSql.getParameterMappings(), parameterObject);
				setParameters(countStmt, mappedStatement, countBS,
						parameterObject);
				ResultSet rs = countStmt.executeQuery();
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				rs.close();
				countStmt.close();
				page.setTotalCount(count);
				if(page.getTotalPage()<=page.getPageNo()){
					page.setPageNo(page.getTotalPage());
				}
				
				String pageSql = generatePageSql(sql, page);
				// System.out.println("page sql:"+pageSql);
				ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
			}
		}
		return ivk.proceed();
	}

	private void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters")
				.object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null
					: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName
							.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(
											propertyName.substring(prop
													.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject
								.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException(
								"There was no TypeHandler found for parameter "
										+ propertyName + " of statement "
										+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value,
							parameterMapping.getJdbcType());
				}
			}
		}
	}

	private String generatePageSql(String sql, Page page) {
		if (page != null && (dialect != null || !dialect.equals(""))) {
			StringBuffer pageSql = new StringBuffer();
			if ("mysql".equals(dialect)) {
				pageSql.append(sql);
				pageSql.append(" limit " + page.getCurrentResult() + ","
						+ page.getPageSize());
			} else if ("oracle".equals(dialect)) {
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				pageSql.append(")  tmp_tb where ROWNUM<=");
				pageSql.append(page.getCurrentResult() + page.getPageSize());
				pageSql.append(") where row_id>");
				pageSql.append(page.getCurrentResult());
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public Object plugin(Object paramObject) {
		return Plugin.wrap(paramObject, this);
	}

	public void setProperties(Properties paramProperties) {
		dialect = paramProperties.getProperty("dialect");
		if (dialect == null || dialect.equals("")) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
		pageSqlId = paramProperties.getProperty("pageSqlId");
		if (dialect == null || dialect.equals("")) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}
}