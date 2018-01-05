package com.fifedu.base.web.view;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

import com.ctakit.common.util.json.JsonUtils;
import com.fifedu.util.constant.Constants;
import com.fifedu.util.web.RequestUtils;

public class MyMappingJacksonJsonView extends AbstractView {
	public static final String DEFAULT_CONTENT_TYPE = "application/json";
	private Set<String> a;
	private boolean b = true;
	private boolean c = false;

	public MyMappingJacksonJsonView() {
		setContentType("application/json");
		setExposePathVariables(false);
	}

	public Set<String> getRenderedAttributes() {
		return this.a;
	}

	public void setRenderedAttributes(Set<String> paramSet) {
		this.a = paramSet;
	}

	public void setDisableCaching(boolean paramBoolean) {
		this.b = paramBoolean;
	}

	protected void prepareResponse(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
		paramHttpServletResponse.setContentType(getContentType());
		paramHttpServletResponse.setCharacterEncoding(Constants.SYSTEM_CHARSET);
		if (this.b) {
			paramHttpServletResponse.addHeader("Pragma", "no-cache");
			paramHttpServletResponse.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
			paramHttpServletResponse.addDateHeader("Expires", 1L);
		}
	}

	protected void renderMergedOutputModel(Map<String, Object> paramMap, HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) {
		try {
			ServletOutputStream localServletOutputStream = paramHttpServletResponse.getOutputStream();

			Object obj = filterModel(paramMap, paramHttpServletRequest);
			writeContent(localServletOutputStream, obj, paramHttpServletRequest, paramHttpServletResponse);
			return;
		} catch (IOException localIOException) {
		}
	}

	protected void writeContent(OutputStream paramOutputStream, Object paramObject, HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws IOException {
		String str1 = paramHttpServletRequest.getParameter("jsonpcallback");
		String str2 = paramHttpServletRequest.getParameter("callback");
		String scriptcallback = paramHttpServletRequest.getParameter("scriptcallback");
		String str3 = paramHttpServletRequest.getParameter("fileUploadReturnJson");
		if (str1 != null) {
			paramHttpServletResponse.setHeader("Content-Type", "text/plain; charset=UTF-8");
			paramOutputStream.write((str1 + "(").getBytes());
			JsonUtils.writeJson(paramOutputStream, paramObject);
			paramOutputStream.write(")".getBytes());
		} else if (str2 != null) {
			paramHttpServletResponse.setHeader("Content-Type", "text/plain; charset=UTF-8");
			paramOutputStream.write((str2 + "(").getBytes());
			JsonUtils.writeJson(paramOutputStream, paramObject);
			paramOutputStream.write(")".getBytes());
		} else if (scriptcallback != null) {
			paramHttpServletResponse.setHeader("Content-Type", "text/html; charset=UTF-8");
			paramOutputStream.write(("<script type=\"text/javascript\">" + scriptcallback + "(").getBytes());
			JsonUtils.writeJson(paramOutputStream, paramObject);
			paramOutputStream.write(")</script>".getBytes());
		} else if (str3 != null) {
			paramHttpServletResponse.setHeader("Content-Type", "text/html; charset=UTF-8");
			JsonUtils.writeJson(paramOutputStream, paramObject);
		} else {
			JsonUtils.writeJson(paramOutputStream, paramObject);
		}
		paramOutputStream.flush();
	}

	protected Object filterModel(Map<String, Object> paramMap, HttpServletRequest paramHttpServletRequest) {
		Map map = RequestUtils.getRequestAttributeMapForModel(paramHttpServletRequest);

		Set localSet = !CollectionUtils.isEmpty(this.a) ? this.a : paramMap.keySet();
		Iterator iterator;
		for (iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry localEntry;
			if ((!((localEntry = (Map.Entry) iterator.next()).getValue() instanceof BindingResult))
					&& (localSet.contains(localEntry.getKey()))) {
				map.put(localEntry.getKey(), localEntry.getValue());
			}
		}
		return map;
	}
}