package com.fifedu.util.page;

import java.io.Serializable;

public class PageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_PAGE_SIZE = 10;

	private Integer pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数

	private Integer curPage = 1; // 当前页

	private Integer totalPage = 0; // 总计有多少页

	private Integer totalCount = 0; // 总记录数

	public Integer getPageSize() {
		return pageSize; 
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	public Integer getTotalPage(){
		if(totalCount==null || pageSize==null)
			return 0;
		int totalPage = totalCount/pageSize;
		int _temp1 = totalCount%pageSize;
		if(_temp1!=0){
			totalPage++;
		}
		return totalPage;
	}
}
