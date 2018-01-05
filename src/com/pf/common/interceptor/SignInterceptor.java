package com.pf.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fifedu.util.sign.SignatureHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fifedu.oper.exception.DbClientException;
import com.fifedu.util.sign.SignConstants;

public class SignInterceptor implements HandlerInterceptor{
	private final Logger log = LoggerFactory.getLogger(SignInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String[]> map= request.getParameterMap();
	     boolean isAllow = false;
		try {
            String signParam = request.getParameter("sign");
            if (signParam==null||signParam.equals("")) {
            	 log.error("sign参数为空");
            	 response.getWriter().println("sign can not be empty");
                throw new DbClientException("sign 参数为空");
            }
            String signCode =  SignatureHelper.sign(map, SignConstants.SIGN_COEDE);
            if(!signCode.equalsIgnoreCase(signParam)){
            	 log.error("sign参数验证失败！");
            	 response.getWriter().println("sign Validation fails");
            }else{
            	isAllow = true;
            }
        } catch (Exception e) {
            log.error("签名验证拦截器异常");
            throw new DbClientException("签名验证拦截器异常");
        }
		return isAllow;	
	}
}
