package com.fifedu.util.page;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 返回：T列表 */
	private List<T> objList;
	/** 分页信息 */
	private PageBean pageBean;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<T> getObjList() {
		return objList;
	}

	public void setObjList(List<T> objList) {
		this.objList = objList;
	}
}
