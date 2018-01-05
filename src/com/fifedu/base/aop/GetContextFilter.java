package com.fifedu.base.aop;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统上下文拦截器,对上下文进行初始化设置.
 * @author liu10
 *
 */
public class GetContextFilter implements Filter{  
	  
    @Override  
    public void destroy() {  
          
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        SysContext.setRequest((HttpServletRequest)request);  
        SysContext.setResponse((HttpServletResponse)response);

        chain.doFilter(request, response);  
    }  
  
    @Override  
    public void init(FilterConfig config) throws ServletException {  
          
    }  
  
}  
