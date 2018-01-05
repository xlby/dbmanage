package com.fifedu.base.service;


import com.fifedu.mybatis.base.BaseDAO;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, PK extends Serializable> {
	/**
	 *  保存
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public T save(T entity);

	/**
	 *  更新
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int update(T entity);

	/**
	 *  根据id删除某个对象
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int deleteById(PK id);

	/**
	 *  根据id批量删除对象
	 * @param ids
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int delete(List<PK> ids);


	/**
	 *  根据id加载某个对象
	 * @param id
	 * @return
	 */
	public T getById(PK id);

	/**
	 *  查找所有的对象
	 * @return
	 */
	public List<T> findAllByPage();
	public List<T> findAllByPage(T entity); 
	
	/**
	 * 按照查询条件查询对象
	 * @return
	 */
	public List<T> findAllByExample(T entity); 

	
	
	public BaseDAO<T, PK> getBaseDAO();

	/**
	 *  根据实体bean信息查询
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<T> getByExample(T entity);
	public List<T> findEntityByPage(T entity);
	
	/**
	 *  根据属性查找
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> getByProperty(String property, Serializable value);
}
