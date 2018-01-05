package com.fifedu.base.aop;

import javax.servlet.http.HttpSession;

import com.fifedu.util.core.ThreadLocalUtils;
import com.fifedu.util.json.JacksonUtil;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.fifedu.base.vo.LoginUserInfo;
import com.fifedu.base.web.MyController;
import com.fifedu.base.web.resolver.ResultType;
import com.fifedu.util.constant.CasRedisDBConstants;

import java.util.Map;

/**
 * service 切面实现类 
 * 实现了调用前后的日志,及异常处理.
 * @author liu10
 *
 */
public class ControllerLog extends MyController{
	private static Logger logger = LoggerFactory.getLogger(ControllerLog.class);
	/**
	 * 调用前切面,记录时间,登录用户,ip,调用方法名日志.
	 * @param joinPoint
	 */
	public void beforeAction(JoinPoint joinPoint){
		 /* HttpSession session = SysContext.getSession();
		  if(session!=null&&session.getAttribute("LoginUserInfo")!=null){
			  User userInfo =    (User) session.getAttribute("LoginUserInfo");
			  logger.info(userInfo.getUserName()+" from ip"+ SysContext.getRequest().getHeader("X-Forwarded-For")+":start::"+joinPoint.getSignature());
		  }*/
		 Object[] args = joinPoint.getArgs();
		 //System.out.println("方法： "+ThreadLocalUtils.getRequest().getMethod());
		StringBuffer sb = new StringBuffer();

		try {
		if(ThreadLocalUtils.getRequest()!=null) {

				if (args != null) {
					if ("GET".equals(ThreadLocalUtils.getRequest().getMethod()) || "get".equals(ThreadLocalUtils.getRequest().getMethod())) {
						for (Object obj : args) {
							sb.append(obj + ",");
						}
					} else {
						//System.out.println("是否为MAP：  "+(args[0] instanceof Map));
						if (args.length > 0) {
							sb.append(JacksonUtil.objToJson(args[0]));
						}
					}
				}

		}
	} catch (Exception e) {
			logger.error("aop start error");
			e.printStackTrace();
	}finally {
			logger.info("aop:start::" + joinPoint.getSignature() + " params:" + sb.toString());
	}


//		logger.info("test before aop log");
//		System.out.println("test before aop log========================================================================");

    }
	/**
	 * 调用后切面,记录登录用户名,调用方法
	 * @param joinPoint
	 */
    public void afterAction(JoinPoint joinPoint){
    	/* HttpSession session = SysContext.getSession();
    	 if(session!=null&&session.getAttribute("LoginUserInfo")!=null){
    		 User userInfo =    (User) session.getAttribute("LoginUserInfo");
    	    	logger.info(userInfo.getUserName()+":end::"+joinPoint.getSignature());
		  }*/
		logger.info("aop:end::"+joinPoint.getSignature());
		/*logger.info("test after aop log");
		System.out.println("test after aop log========================================================================");*/
    }


    /**
     * 异常抛出切面,暂未做记录.
     * @param joinPoint
     */
    public void eAction(JoinPoint joinPoint){
 
    }


    /*******************************xlan*****************************************/

	public void log() {
		System.out.println("*************Log*******************");
	}

	//有参无返回值的方法
	public void logArg(JoinPoint point) {
		//此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		Object[] args = point.getArgs();
		System.out.println("目标参数列表：");
		if (args != null) {
			for (Object obj : args) {
				System.out.println(obj + ",");
			}
			System.out.println();
		}
	}

	//有参并有返回值的方法
	public void logArgAndReturn(JoinPoint point, Object returnObj) {
		//此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		Object[] args = point.getArgs();
		System.out.println("目标参数列表：");
		if (args != null) {
			for (Object obj : args) {
				System.out.println(obj + ",");
			}
			System.out.println();
			System.out.println("执行结果是：" + returnObj);
		}
	}

}
