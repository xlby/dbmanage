package com.fifedu.base.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ctakit.common.util.MyStringUtils;
import com.ctakit.common.util.page.imp.Page;
/**
 * 分页拦截器,对基本分页信息进行记录,供分页查询使用.
 * @author liu10
 *
 */
public class PaginationInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object obj)
			throws Exception {
		String pageSize = paramHttpServletRequest.getParameter("pageSize");
		if(pageSize==null||"".equals(pageSize)){
			pageSize="10";
		}
		String pageNo = paramHttpServletRequest.getParameter("pageNo");
		Page page = null;
		if((pageSize != null && MyStringUtils.isNumericSpace(pageSize))||(pageNo != null && MyStringUtils.isNumericSpace(pageNo))) {
			page = new Page();
			if ((pageSize != null) && (MyStringUtils.isNumericSpace(pageSize))) {
				page.setPageSize(Integer.parseInt(pageSize));
			}
			if ((pageNo != null) && (MyStringUtils.isNumericSpace(pageNo))) {
				page.setPageNo(Integer.parseInt(pageNo));
			}
		}
		Page.threadLocal.set(page);
		return true;
	}

	public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject,
			ModelAndView paramModelAndView) throws Exception {
		super.postHandle(paramHttpServletRequest, paramHttpServletResponse, paramObject, paramModelAndView);
		Page page = Page.threadLocal.get();
		if (page != null) {
			if(page.getData()!=null) {
				paramHttpServletRequest.setAttribute("page", page);
			}
			Page.threadLocal.remove();
		}
	}
}