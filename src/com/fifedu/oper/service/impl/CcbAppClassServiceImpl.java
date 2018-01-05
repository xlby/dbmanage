package com.fifedu.oper.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifedu.base.service.impl.BaseServiceImpl;
import com.fifedu.mybatis.base.BaseDAO;
import com.fifedu.mybatis.dao.CcbAppClassDAO;
import com.fifedu.mybatis.model.ICcbAppClass;
import com.fifedu.mybatis.model.base.CcbAppClass;
import com.fifedu.oper.service.CcbAppClassService;
import com.iflytek.cycore.usercenter.api.TClass;
import com.iflytek.iplat.uc.ws.IClassService;


@Service
public class CcbAppClassServiceImpl extends BaseServiceImpl<CcbAppClass,String>  implements CcbAppClassService{

	@Override
	public BaseDAO<CcbAppClass, String> getBaseDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addClassInTable(List<String> classIds, String userName, Integer roleId, String schoolId,
			Integer grade, String schoolCode) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}