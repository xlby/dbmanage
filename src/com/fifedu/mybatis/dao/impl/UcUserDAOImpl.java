package com.fifedu.mybatis.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.fifedu.mybatis.dao.UcUserDAO;
import com.fifedu.mybatis.base.BaseDAOImpl;
import com.fifedu.mybatis.base.BaseMapper;
import com.fifedu.mybatis.mapper.UcUserMapper;
import com.fifedu.mybatis.model.base.UcUser;
import com.fifedu.oper.DbParam;

@Repository
public class UcUserDAOImpl extends BaseDAOImpl<UcUser,String> implements UcUserDAO {

	@Resource
	private UcUserMapper ucUserMapper;

	@Override
	public BaseMapper<UcUser, String> getMapper() {
		return ucUserMapper;
	}

	@Override
	public List<DbParam> getDbCodeInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ucUserMapper.getDbCodeInfo(map);
	}
	
}
