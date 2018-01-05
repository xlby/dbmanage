package com.fifedu.base.web.view;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.BeansException;
import org.springframework.oxm.Marshaller;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.fifedu.util.web.RequestUtils;

public class MyMarshallingView extends AbstractView {
	public static final String DEFAULT_CONTENT_TYPE = "application/xml";
	private Marshaller a;
	private String b;

	public MyMarshallingView() {
		setContentType("application/xml");
	}

	public MyMarshallingView(Marshaller paramMarshaller) {
		Assert.notNull(paramMarshaller, "'marshaller' must not be null");
		setContentType("application/xml");
		this.a = paramMarshaller;
	}

	public void setMarshaller(Marshaller paramMarshaller) {
		Assert.notNull(paramMarshaller, "'marshaller' must not be null");
		this.a = paramMarshaller;
	}

	public void setModelKey(String paramString) {
		this.b = paramString;
	}

	protected void initApplicationContext() throws BeansException {
		Assert.notNull(this.a, "Property 'marshaller' is required");
	}

	protected void renderMergedOutputModel(Map<String, Object> paramMap, HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		HashMap localHashMap = new HashMap();
		Map lmap = RequestUtils.getRequestAttributeMapForModel(paramHttpServletRequest);
		lmap.putAll(paramMap);
		localHashMap.put("root", lmap);
		Object obj = null;
		if ((obj = (Map<String, Object>) locateToBeMarshalled(localHashMap)) == null) {
			throw new ServletException("Unable to locate object to be marshalled in model: " + localHashMap);
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
		this.a.marshal(obj, new StreamResult(baos));

		paramHttpServletResponse.setContentType(getContentType());
		paramHttpServletResponse.setContentLength(baos.size());

		FileCopyUtils.copy(baos.toByteArray(), paramHttpServletResponse.getOutputStream());
	}

	protected Object locateToBeMarshalled(Map<String, Object> paramMap) throws ServletException {
		if (this.b != null) {
			Object obj = null;
			if ((obj = paramMap.get(this.b)) == null) {
				throw new ServletException("Model contains no object with key [" + this.b + "]");
			}
			if (!this.a.supports(paramMap.getClass())) {
				throw new ServletException("Model object [" + paramMap + "] retrieved via key [" + this.b
						+ "] is not supported by the Marshaller");
			}
			return paramMap;
		}
		Iterator obj = null;
		for (obj = paramMap.values().iterator(); obj.hasNext();) {
			Object localObject;
			if (((localObject = obj.next()) != null) && (this.a.supports(localObject.getClass()))) {
				return localObject;
			}
		}
		return null;
	}
}