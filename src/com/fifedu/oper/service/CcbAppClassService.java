package com.fifedu.oper.service;

import java.util.List;
import java.util.Map;

import com.fifedu.base.service.BaseService;
import com.fifedu.mybatis.model.ICcbAppClass;
import com.fifedu.mybatis.model.base.CcbAppClass;

public interface CcbAppClassService extends BaseService<CcbAppClass, String>{
	
	/**
	 * 插入班级与用户关联表信息数据库表
	 * @param classIds
	 * @param userName
	 * @param roleId
	 * @return
	 */
	public boolean addClassInTable(List<String> classIds,String userName,Integer roleId,String schoolId,Integer grade,String schoolCode);
	
}
