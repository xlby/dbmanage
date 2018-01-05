package com.fifedu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.fifedu.mybatis.base.BaseMapper;
import com.fifedu.mybatis.model.base.UcUser;
import com.fifedu.oper.DbParam;

public interface UcUserMapper extends BaseMapper<UcUser, String> {
  
	
	/**
	 * 通过实体类作为参数，获取report的信息
	 * @param report
	 * @return
	 */
	public List<DbParam> getDbCodeInfo(Map<String,Object> map);
}