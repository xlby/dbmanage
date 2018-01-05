package com.fifedu.util.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ctakit.common.util.MyNumberUtils;
import com.ctakit.common.util.MyStringUtils;
import com.fifedu.util.constant.Constants;

public class CookieUtils {
	protected static final Logger logger = LoggerFactory
			.getLogger(CookieUtils.class);
	public static final String COOKIE_PAGE_SIZE = "_cookie_page_size";
	public static final int DEFAULT_SIZE = 20;
	public static final int MAX_SIZE = 200;
	public static String path = "/";
 
	public static int getPageSize(HttpServletRequest paramHttpServletRequest) {
		Assert.notNull(paramHttpServletRequest);
		Cookie cookie = getCookie(paramHttpServletRequest, "_cookie_page_size");
		int i = 0;
		if ((paramHttpServletRequest != null)
				&& (MyNumberUtils.isDigits(cookie.getValue()))) {
			i = Integer.parseInt(cookie.getValue());
		}
		if (i <= 0) {
			i = 20;
		} else if (i > 200) {
			i = 200;
		}
		return i;
	}

	public static Cookie getCookie(HttpServletRequest paramHttpServletRequest,
			String paramString) {
		Assert.notNull(paramHttpServletRequest);
		Cookie[] cookie = paramHttpServletRequest.getCookies();
		if ((cookie != null) && (cookie.length > 0)) {
			int i = cookie.length;
			for (int j = 0; j < i; j++) {
				Cookie localCookie;
				if ((localCookie = cookie[j]).getName().equals(paramString)) {
					return localCookie;
				}
			}
		}
		return null;
	}

	public static void clearCookie(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString) {
		Assert.notNull(paramHttpServletRequest);
		Cookie[] arrayOfCookie;
		if (((arrayOfCookie = paramHttpServletRequest.getCookies()) != null)
				&& (arrayOfCookie.length > 0)) {
			try {
				for (Cookie localCookie : arrayOfCookie) {
					addCookie(paramHttpServletRequest,
							paramHttpServletResponse, localCookie.getName(),
							null, Integer.valueOf(0), paramString);
				}
				return;
			} catch (Exception localException) {
				logger.error("清空Cookies发生异常！");
			}
		}
	}

	public static Cookie addCookie(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString1,
			String paramString2, Integer paramInteger, String paramString3) {
		return addCookie(paramHttpServletRequest, paramHttpServletResponse,
				paramString1, paramString2, paramInteger, paramString3, false);
	}

	public static Cookie addCookie(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString1,
			String paramString2, Integer paramInteger, String paramString3,
			boolean paramBoolean) {
		try {
			paramString2 = URLEncoder.encode(paramString2,
					Constants.SYSTEM_CHARSET);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			localUnsupportedEncodingException.printStackTrace();
		}
		Cookie cookie = new Cookie(paramString1, paramString2);
		if (paramInteger != null) {
			cookie.setMaxAge(paramInteger.intValue());
		}
		if (MyStringUtils.isNotBlank(paramString3)) {
			cookie.setDomain(paramString3);
		}
		cookie.setHttpOnly(paramBoolean);

		cookie.setPath(path);
		paramHttpServletResponse.addCookie(cookie);
		return cookie;
	}

	public static void removeCookie(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString1,
			String paramString2) {
		Cookie cookie = new Cookie(paramString1, "");
		cookie.setMaxAge(0);
		cookie.setPath(path);
		if (MyStringUtils.isNotBlank(paramString2)) {
			cookie.setDomain(paramString2);
		}
		paramHttpServletResponse.addCookie(cookie);
	}
}