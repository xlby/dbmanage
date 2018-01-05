package com.fifedu.base.service.impl;


import com.fifedu.base.service.BaseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T, PK> implements BaseService<T, String> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 保存对象
	 */
	public T save(T entity) {
		return this.getBaseDAO().save(entity);
	}

	/**
	 * 更新对象
	 */
	@Override
	public int update(T entity) {
		return this.getBaseDAO().update(entity);

	}

	/**
	 * 根据ID删除对象
	 */
	@Override
	public int deleteById(String id) {
		return this.getBaseDAO().deleteById(id);

	}

	/**
	 * 根据ID批量删除对象
	 */
	@Override
	public int delete(List<String> ids) {
		return this.getBaseDAO().delete(ids);

	}

	/**
	 *根据ID查找对象
	 */
	@Override
	public T getById(String id) {
		return this.getBaseDAO().getById(id);
	}

	/**
	 * 查找所有对象
	 */
	public List<T> findAllByPage() {
		return this.getBaseDAO().findAllByPage();
	}
	
	/**
	 * 按照条件查询
	 * */
	public List<T> findAllByExample(T entity){
	    return this.getBaseDAO().getByExample(entity);
	}
	@Override
	public List<T> findAllByPage(T entity) {
	   return this.getBaseDAO().findAllByPage(entity);
	}

	/**
	 * 根据条件查询
	 */
	public List<T> getByExample(T entity) {
		return this.getBaseDAO().getByExample(entity);
	}
	public List<T> findEntityByPage(T entity){
	    return this.getBaseDAO().findEntityByPage(entity);
	};
	/**
	 * 根据属性值查找对象
	 */
	@Override
	public List<T> getByProperty(String property, Serializable value) {
		return this.getBaseDAO().getByProperty(property, value);
	}
}
