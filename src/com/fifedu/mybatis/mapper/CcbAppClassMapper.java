package com.fifedu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.fifedu.mybatis.base.BaseMapper;
import com.fifedu.mybatis.model.ICcbAppClass;
import com.fifedu.mybatis.model.base.CcbAppClass;


public interface CcbAppClassMapper extends BaseMapper<CcbAppClass, String> {
  
	/**
	 * 插入班级信息表基本信息
	 * @param entity
	 * @return
	 */
	public int insertAppClass(Map<String, Object> map);
	
	/**
	 * 判断班级信息表的数据是否存在
	 * @param map
	 * @return
	 */
	public int isExistInClassTable(Map<String, Object> map);
	
	/**
	 * 通过学校ID，获取班级列表
	 * @return
	 */
	public List<ICcbAppClass> getTClassArrayBySchool(Map<String, Object> map);
	
	/**
	 * 通过班级ID，学校Id，获取用户数量
	 * @param map
	 * @return
	 */
	public Integer getTStudentArrayByClass(Map<String, Object> map);
}