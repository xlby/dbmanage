package com.pf.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 解决跨域访问的浏览器拦截问题
 * 在响应的头部增加Access-Control-Allow-Origin 信息
 * @author lz
 *
 */
public class HeaderFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Authentication");
		arg2.doFilter(request, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
