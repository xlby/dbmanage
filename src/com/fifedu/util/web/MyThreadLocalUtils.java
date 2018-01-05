package com.fifedu.util.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fifedu.util.core.ThreadLocalUtils;

/**
 * 线程变量 辅助类
 * 
 * 
 * 
 */
public class MyThreadLocalUtils extends ThreadLocalUtils {

	/**
	 * userType 用户类型 0 普通用户; 1 手机用户
	 */
	private static ThreadLocal<Integer> userType = new ThreadLocal<Integer>();
	private static ThreadLocal<String> uriExt = new ThreadLocal<String>();
	private static ThreadLocal<String> token = new ThreadLocal<String>();

	private static Logger log = LoggerFactory
			.getLogger(MyThreadLocalUtils.class);

	public static void setToken(String value) {
		token.set(value);
	}

	public static String getToken() {
		return token.get();
	}

	public static void removeToken() {
		token.remove();
	}

	public static void setUriExt(String value) {
		uriExt.set(value);
	}

	public static String getUriExt() {
		return uriExt.get();
	}

	public static void removeUriExt() {
		uriExt.remove();
	}

	public static void setUserType(int value) {
		userType.set(value);
	}

	public static int getUserType() {
		if (userType.get() == null) {
			return 0;
		}
		return userType.get();
	}

	public static void removeUserType() {
		userType.remove();
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
