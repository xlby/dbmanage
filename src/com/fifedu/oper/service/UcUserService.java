package com.fifedu.oper.service;

import com.fifedu.mybatis.model.base.UcUser;
import com.fifedu.oper.DbParam;

import java.util.List;
import java.util.Map;

import com.fifedu.base.service.BaseService;

public interface UcUserService extends BaseService<UcUser, String>{
	
	
	/**
	 * 通过实体类作为参数，获取report的信息
	 * @param report
	 * @return
	 */
	public List<DbParam> getDbCodeInfo(Map<String,Object> map);
}
