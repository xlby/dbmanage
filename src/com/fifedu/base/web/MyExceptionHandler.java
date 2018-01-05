package com.fifedu.base.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.parameters.ParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 异常处理
 * @author liu10
 *
 */
public class MyExceptionHandler implements HandlerExceptionResolver {  
  /**
   * 异常处理,不同请求,返回不同内容.
   */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
        Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex",ex);
        model.put("exMsg",ex.getMessage());
    
        String ss =request.getHeader("x-requested-with");
        if(ss!=null&&!"".equals(ss)){
        	 return new ModelAndView("json", model);  
        }
        // 根据不同错误转向不同页面  
         return new ModelAndView("ftl:example/error", model);  
    }  
}  