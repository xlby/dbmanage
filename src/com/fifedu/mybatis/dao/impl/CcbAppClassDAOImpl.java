package com.fifedu.mybatis.dao.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.fifedu.mybatis.base.BaseDAOImpl;
import com.fifedu.mybatis.base.BaseMapper;
import com.fifedu.mybatis.dao.CcbAppClassDAO;
import com.fifedu.mybatis.mapper.CcbAppClassMapper;
import com.fifedu.mybatis.model.ICcbAppClass;
import com.fifedu.mybatis.model.base.CcbAppClass;


@Repository
public class CcbAppClassDAOImpl extends BaseDAOImpl<CcbAppClass,String> implements CcbAppClassDAO {

	@Resource
	private CcbAppClassMapper ccbAppClassMapper;

	@Override
	public BaseMapper<CcbAppClass, String> getMapper() {
		return ccbAppClassMapper;
	}

	@Override
	public int insertAppClass(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ccbAppClassMapper.insertAppClass(map);
	}

	@Override
	public int isExistInClassTable(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ccbAppClassMapper.isExistInClassTable(map);
	}

	@Override
	public List<ICcbAppClass> getTClassArrayBySchool(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ccbAppClassMapper.getTClassArrayBySchool(map);
	}

	@Override
	public Integer getTStudentArrayByClass(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ccbAppClassMapper.getTStudentArrayByClass(map);
	}
	
}
