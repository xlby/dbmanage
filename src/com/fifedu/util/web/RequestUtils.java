package com.fifedu.util.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import com.ctakit.common.util.MyFileUtils;
import com.ctakit.common.util.MyStringUtils;
import com.ctakit.common.util.Resources;
import com.fifedu.util.constant.Constants;

public class RequestUtils {
	private static final Logger a = LoggerFactory.getLogger(RequestUtils.class);
	private static String[] b = Resources.getString(
			"sys.model.request.atrribute", "page,error,errors,message").split(
			",");

	public static String getQueryParam(
			HttpServletRequest paramHttpServletRequest, String paramString) {
		if (MyStringUtils.isBlank(paramString)) {
			return null;
		}
		if (paramHttpServletRequest.getMethod().equalsIgnoreCase("POST")) {
			return paramHttpServletRequest.getParameter(paramString);
		}
		String queryString = paramHttpServletRequest.getQueryString();
		if (MyStringUtils.isBlank(queryString)) {
			return null;
		}
		try {
			queryString = URLDecoder.decode(queryString,
					Constants.SYSTEM_CHARSET);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			a.error("encoding " + Constants.SYSTEM_CHARSET + " not support?",
					localUnsupportedEncodingException);
		}
		String[] arrayOfString;
		if (((arrayOfString = (String[]) parseQueryString(queryString).get(
				paramString)) != null)
				&& (arrayOfString.length > 0)) {
			return arrayOfString[(arrayOfString.length - 1)];
		}
		return null;
	}

	/*
	 * public static Map<String, Object> getQueryParams(HttpServletRequest
	 * paramHttpServletRequest) { Map paramMap = null; String queryString =
	 * null; if (paramHttpServletRequest.getMethod().equalsIgnoreCase("POST")) {
	 * paramMap = paramHttpServletRequest.getParameterMap(); } else { if
	 * (MyStringUtils.isBlank(queryString =
	 * paramHttpServletRequest.getQueryString())) { return new HashMap(); } try
	 * { queryString = URLDecoder.decode((String)queryString, "UTF-8"); } catch
	 * (UnsupportedEncodingException exce) {
	 * a.error("encoding UTF-8 not support?", exce); } paramMap =
	 * parseQueryString(queryString); } Map tmpMap = new
	 * HashMap(paramMap.size()); for (Iterator localIterator =
	 * paramMap.entrySet().iterator(); localIterator.hasNext();) { Map.Entry
	 * localEntry; if ((queryString = ((String[])(localEntry =
	 * (Map.Entry)localIterator.next()).getValue()).length) == 1) {
	 * ((Map)paramMap).put(localEntry.getKey(),
	 * ((String[])localEntry.getValue())[0]); } else if (paramHttpServletRequest
	 * > 1) { ((Map)paramMap).put(localEntry.getKey(), localEntry.getValue()); }
	 * } return paramMap; }
	 */
	public static Map<String, String[]> parseQueryString(String queryString) {
		if (null == queryString || "".equals(queryString)) {
			return null;
		}
		Map m = new HashMap();
		String[] strArray = queryString.split("&");
		for (int index = 0; index < strArray.length; index++) {
			String pair = strArray[index];
			putMapByPair(pair, m);
		}

		return m;
	}

	/**
	 * 把键值添加至Map<br/>
	 * pair:name=value
	 * 
	 * @param pair
	 *            name=value
	 * @param m
	 */
	public static void putMapByPair(String pair, Map m) {

		if (null == pair || "".equals(pair)) {
			return;
		}
		int indexOf = pair.indexOf("=");
		if (-1 != indexOf) {
			String k = pair.substring(0, indexOf);
			String v = pair.substring(indexOf + 1, pair.length());
			if (null != k && !"".equals(k)) {
				m.put(k, v);
			}
		} else {
			m.put(pair, "");
		}
	}

	public static Map<String, String> getRequestMap(
			HttpServletRequest paramHttpServletRequest, String paramString) {
		return a(paramHttpServletRequest, paramString, false);
	}

	public static Map<String, String> getRequestMapWithPrefix(
			HttpServletRequest paramHttpServletRequest, String paramString) {
		return a(paramHttpServletRequest, paramString, true);
	}

	private static Map<String, String> a(
			HttpServletRequest paramHttpServletRequest, String paramString,
			boolean paramBoolean) {
		HashMap localHashMap = new HashMap();
		Enumeration localEnumeration = paramHttpServletRequest
				.getParameterNames();
		while (localEnumeration.hasMoreElements()) {
			String str1;
			if ((str1 = (String) localEnumeration.nextElement())
					.startsWith(paramString)) {
				String str2 = paramBoolean ? str1 : str1.substring(paramString
						.length());
				str1 = MyStringUtils.join(
						paramHttpServletRequest.getParameterValues(str1), ',');
				localHashMap.put(str2, str1);
			}
		}
		return localHashMap;
	}

	public static Map<String, Object> getRequestAttributeMap(
			HttpServletRequest paramHttpServletRequest, String paramString,
			boolean paramBoolean) {
		HashMap localHashMap = new HashMap();
		Enumeration localEnumeration = paramHttpServletRequest
				.getAttributeNames();
		while (localEnumeration.hasMoreElements()) {
			String str1;
			if ((str1 = (String) localEnumeration.nextElement())
					.startsWith(paramString)) {
				String str2 = paramBoolean ? str1 : str1.substring(paramString
						.length());
				localHashMap.put(str2,
						paramHttpServletRequest.getAttribute(str1));
			}
		}
		return localHashMap;
	}

	public static Map<String, Object> getRequestAttributeMapForModel(
			HttpServletRequest paramHttpServletRequest) {
		HashMap localHashMap = new HashMap();
		for (String str : b) {
			Object localObject;
			if ((localObject = paramHttpServletRequest.getAttribute(str)) != null) {
				localHashMap.put(str, localObject);
			}
		}
		return localHashMap;
	}

	public static String getIpAddr(HttpServletRequest paramHttpServletRequest) {
		String str;
		if ((!MyStringUtils.isBlank(str = paramHttpServletRequest
				.getHeader("X-Real-IP"))) && (!"unknown".equalsIgnoreCase(str))) {
			return str;
		}
		if ((!MyStringUtils.isBlank(str = paramHttpServletRequest
				.getHeader("X-Forwarded-For")))
				&& (!"unknown".equalsIgnoreCase(str))) {
			int index = -1;
			if ((index = str.indexOf(',')) != -1) {
				return str.substring(0, index);
			}
			return str;
		}
		return paramHttpServletRequest.getRemoteAddr();
	}

	public static String getLocation(HttpServletRequest paramHttpServletRequest) {
		UrlPathHelper localUrlPathHelper = new UrlPathHelper();
		StringBuffer localStringBuffer = paramHttpServletRequest
				.getRequestURL();
		String str1 = paramHttpServletRequest.getRequestURI();
		String str2 = localUrlPathHelper
				.getOriginatingRequestUri(paramHttpServletRequest);
		String queryString = "";
		localStringBuffer.replace(localStringBuffer.length() - str1.length(),
				localStringBuffer.length(), str2);
		if ((queryString = localUrlPathHelper
				.getOriginatingQueryString(paramHttpServletRequest)) != null) {
			localStringBuffer.append("?").append(queryString);
		}
		return localStringBuffer.toString();
	}

	public static String getRequestedSessionId(
			HttpServletRequest paramHttpServletRequest) {
		String str1 = paramHttpServletRequest.getRequestedSessionId();
		String str2 = paramHttpServletRequest.getContextPath();
		if ((paramHttpServletRequest.isRequestedSessionIdFromURL())
				|| (MyStringUtils.isBlank(str2))) {
			return str1;
		}
		Cookie cookie;
		if ((cookie = CookieUtils.getCookie(paramHttpServletRequest,
				Constants.Cookie.SESSIONID)) != null) {
			return cookie.getValue();
		}
		return null;
	}

	public static void addError(HttpServletRequest paramHttpServletRequest,
			String paramString1, String paramString2) {
		Map localObject;
		if ((localObject = (Map) paramHttpServletRequest.getAttribute("errors")) == null) {
			localObject = new LinkedHashMap();
			paramHttpServletRequest.setAttribute("errors", localObject);
		}
		localObject.put(paramString1, paramString2);
	}

	public static String getLookupPath(
			HttpServletRequest paramHttpServletRequest)
			throws IllegalStateException {
		UrlPathHelper localUrlPathHelper = new UrlPathHelper();
		return localUrlPathHelper
				.getLookupPathForRequest(paramHttpServletRequest);
	}

	public static String getLookupPathNoExtension(
			HttpServletRequest paramHttpServletRequest)
			throws IllegalStateException {
		UrlPathHelper localUrlPathHelper;
		return MyFileUtils
				.getFileNameNoExtension((localUrlPathHelper = new UrlPathHelper())
						.getLookupPathForRequest(paramHttpServletRequest));
	}

	public static String getLookupPathExtension(
			HttpServletRequest paramHttpServletRequest)
			throws IllegalStateException {
		UrlPathHelper localUrlPathHelper;
		return MyFileUtils
				.getFileNameExtension((localUrlPathHelper = new UrlPathHelper())
						.getLookupPathForRequest(paramHttpServletRequest));
	}
}
