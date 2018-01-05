package com.fifedu.base.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.ctakit.common.util.MyFileUtils;
import com.fifedu.base.web.resolver.ResultType;
import com.fifedu.util.core.ThreadLocalUtils;
import com.fifedu.util.web.RequestUtils;

public abstract class MyController {
	public Logger logger = LoggerFactory.getLogger(getClass());
	public String JSON = "json";
	public String XML = "xml";
	private static boolean isLookupPath = true;
	private static boolean b = false;
	private static String c = "format";
	@Resource
	protected MessageSource messageSource;

	protected String result(ResultType paramResultType) {
		return result(paramResultType, null);
	}

	protected String result(ResultType paramResultType, String paramString) {
		String str = null;
		if (isLookupPath) {
			str = RequestUtils.getLookupPathExtension(ThreadLocalUtils.getRequest());
		}
		if (paramResultType == null) {
			paramResultType = ResultType.JSP;
		}
		if (str == null) {
			if ((ResultType.JSON.equals(paramResultType)) || (ResultType.XML.equals(paramResultType))) {
				return paramResultType.name().toLowerCase();
			}
		} else if ((this.JSON.equalsIgnoreCase(str)) || ("XML".equalsIgnoreCase(str))) {
			return str;
		}
		if ((ResultType.REDIRECT.equals(paramResultType)) || (ResultType.FORWARD.equals(paramResultType))) {
			paramString = paramString + MyFileUtils.getFileNameFullExtension(RequestUtils.getLookupPath(ThreadLocalUtils.getRequest()), "");
		}
		return paramResultType.name().toLowerCase() + ":" + paramString;
	}
}