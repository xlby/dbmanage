package com.fifedu.base.web;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.ServletContextAware;

import com.ctakit.common.util.Resources;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 初始化系统相关数据,Redis数据的初始化，可以从redis里读取、删除数据
 */
public class InitSystemData implements InitializingBean , ServletContextAware{
    public ServletContext context;
	public Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Init systemData");
		this.context.setAttribute("baseURL", Resources.getString("sys.url.baseURL"));
		this.context.setAttribute("staticURL", Resources.getString("sys.url.staticURL"));
		//this.context.setAttribute("uploadURL", Resources.getString("sys.pfcollect.prefix"));
		//this.context.setAttribute("iscBaseURL", Resources.getString("sys.url.iscBaseURL"));
		//this.context.setAttribute("rev_release", Resources.getLong("sys.static.revRelease",System.currentTimeMillis()));
		//this.context.setAttribute("copyright", Resources.getString("copyright",""));
		//this.context.setAttribute("doorURL",Resources.getString("sys.door.url"));
		this.context.setAttribute("iplatServerPath",Resources.getString("iplat.root.server"));
		//this.context.setAttribute("ccbHeadPath",Resources.getString("sys.headpath.upload"));
	}
		

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
	
	
}
