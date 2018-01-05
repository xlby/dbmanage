package com.fifedu.mybatis.base;

import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T, PK extends Serializable> {

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	/**
	 * 得到主键值（保存实体时调用）
	 * 
	 * @param entity
	 * @return
	 */
	public Long getNextPrimaryKey();

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	public int update(T entity);

	/**
	 * 更新属性值
	 * 
	 * @param id
	 * @param property
	 * @param value
	 * @return
	 */
	public int updateByProperty(@Param("id") Serializable id,
			@Param("property") String property,
			@Param("value") Serializable value);

	/**
	 * 根据id删除某个对象
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(@Param("id") PK id);

	/**
	 * 根据id批量删除对象
	 * 
	 * @param ids
	 * @return
	 */
	public int delete(@Param("ids") List<PK> ids);

	/**
	 * 根据id批量锁定对象
	 * 
	 * @param ids
	 * @return
	 */
	public int lock(@Param("ids") List<PK> ids, @Param("locker") String locker);

	/**
	 * 根据属性批量删除对象
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public int deleteByProperty(@Param("property") String property,
			@Param("value") Serializable value);

	/**
	 * 根据id获取某个对象
	 * 
	 * @param id
	 * @return
	 */
	public T getById(@Param("id") PK id);

	/**
	 * 根据属性查找对象
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> getByProperty(@Param("property") String property,
			@Param("value") Serializable value);

	/**
	 * 根据样例对象
	 * 
	 * @param id
	 * @return
	 */
	public List<T> getByExample(T entity);

	/**
	 * 分页查找所有对象
	 * 
	 * @return
	 */
	public List<T> findAllByPage();

	public List<T> findAllByPage(T entity);

	public List<T> findEntityByPage(T entity);

	public List<T> findAllCheckUserByPage(T entity);
}