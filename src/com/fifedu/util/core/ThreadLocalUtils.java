package com.fifedu.util.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ThreadLocalUtils {
	private static ThreadLocal<HttpServletRequest> a = new ThreadLocal();
	private static ThreadLocal<HttpServletResponse> b = new ThreadLocal();
	private static ThreadLocal<HttpSession> c = new ThreadLocal();
	private static ThreadLocal<String> d = new ThreadLocal();

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) a.get();
	}

	public static void setRequest(HttpServletRequest paramHttpServletRequest) {
		a.set(paramHttpServletRequest);
	}

	public static void removeRequest() {
		a.remove();
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) b.get();
	}

	public static void setResponse(HttpServletResponse paramHttpServletResponse) {
		b.set(paramHttpServletResponse);
	}

	public static void removeResponse() {
		b.remove();
	}

	public static HttpSession getSession() {
		return (HttpSession) c.get();
	}

	public static void setSession(HttpSession paramHttpSession) {
		c.set(paramHttpSession);
	}

	public static void removeSession() {
		c.remove();
	}

	public static String getUri() {
		return (String) d.get();
	}

	public static void setUri(String paramString) {
		d.set(paramString);
	}

	public static void removeUri() {
		d.remove();
	}
}