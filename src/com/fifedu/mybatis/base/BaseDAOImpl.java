package com.fifedu.mybatis.base;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseDAOImpl<T extends BaseBean, PK> implements
		BaseDAO<T, String> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected Class<?> entityClass;

	public BaseDAOImpl() {
		super();
		// this.entityClass = (Class<?>) ((ParameterizedType)
		// getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 保存对象
	 */
	public T save(T entity) {
		// entity.setId(String.valueOf(this.getMapper().getNextPrimaryKey()));
		this.getMapper().insert(entity);
		return entity;
	}

	/**
	 * 更新对象
	 */
	@Override
	public int update(T entity) {
		return this.getMapper().update(entity);
	}

	/**
	 * 通过id修改属性值
	 */
	@Override
	public int updatePropertyById(Serializable id, String property,
			Serializable value) {
		return this.getMapper().updateByProperty(id, property, value);
	}

	/**
	 * 根据id删除对象
	 */
	@Override
	public int deleteById(String id) {
		return this.getMapper().deleteById(id);

	}

	/**
	 * 根据id列表删除对象
	 */
	@Override
	public int delete(List<String> ids) {
		return this.getMapper().delete(ids);
	}

	/**
	 * 根据id列表锁定对象
	 */
	@Override
	public int lock(List<String> ids, String locker) {
		return this.getMapper().lock(ids, locker);
	}

	/**
	 * 根据属性值删除对象
	 */
	@Override
	public int deleteByProperty(String property, Serializable value) {
		return this.getMapper().deleteByProperty(property, value);
	}

	/**
	 * 根据id查找对象
	 */
	@Override
	public T getById(String id) {
		return this.getMapper().getById(id);
	}

	/**
	 * 根据样例查找
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> getByExample(T entity) {
		return this.getMapper().getByExample(entity);
	}

	/**
	 * 根据属性值查找对象
	 */
	@Override
	public List<T> getByProperty(String property, Serializable value) {
		return this.getMapper().getByProperty(property, value);
	}

	@Override
	public List<T> findAllByPage() {
		return this.getMapper().findAllByPage();
	}

	@Override
	public List<T> findAllByPage(T entity) {
		return this.getMapper().findAllByPage(entity);
	}

	@Override
	public List<T> findEntityByPage(T entity) {
		return this.getMapper().findEntityByPage(entity);
	}

	@Override
	public List<T> findAllCheckUserByPage(T entity) {
		return this.getMapper().findAllCheckUserByPage(entity);
	}
}