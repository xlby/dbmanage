package com.fifedu.oper.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fifedu.mybatis.dao.UcUserDAO;
import com.fifedu.mybatis.base.BaseDAO;
import com.fifedu.mybatis.model.base.UcUser;
import com.fifedu.oper.DbParam;
import com.fifedu.oper.service.UcUserService;
import com.fifedu.base.service.impl.BaseServiceImpl;

@Service
public class UcUserServiceImpl extends BaseServiceImpl<UcUser,String>  implements UcUserService{
	@Resource
	private UcUserDAO ucUserDAO;
	
	@Override
	public BaseDAO<UcUser, String> getBaseDAO() {
		return ucUserDAO;
	}

	@Override
	public List<DbParam> getDbCodeInfo(Map<String, Object> map) {
		
		/*List<DbParam> list = new ArrayList<DbParam>();
		
		//list.add(new DbParam("1","1","1","1","1","1","1","122"));
		//list.add(new DbParam("2","11","11","11","11","11","11","1122"));
		return list;*/
		// TODO Auto-generated method stub
		return ucUserDAO.getDbCodeInfo(map);
	}

}