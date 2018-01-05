package com.fifedu.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.fifedu.mybatis.base.BaseDAO;
import com.fifedu.mybatis.model.base.UcUser;
import com.fifedu.oper.DbParam;

public interface UcUserDAO extends BaseDAO<UcUser, String> {
	
	/**
	 * 通过实体类作为参数，获取report的信息
	 * @param report
	 * @return
	 */
	public List<DbParam> getDbCodeInfo(Map<String,Object> map);
}
