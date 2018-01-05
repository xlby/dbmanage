package com.fifedu.base.web.resolver;

import java.util.Locale;
import java.util.Map;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class MultipleViewResolver implements ViewResolver {
	private Map<String, ViewResolver> a;
	private String b = null;

	public void setDefaultViewResolverName(String paramString) {
		this.b = paramString;
	}

	public String getDefaultViewResolverName() {
		return this.b;
	}

	public void setResolvers(Map<String, ViewResolver> paramMap) {
		this.a = paramMap;
	}

	public View resolveViewName(String paramString, Locale paramLocale) throws Exception {
		ViewResolver localObject;
		if ((paramString.equalsIgnoreCase("json")) || (paramString.equalsIgnoreCase("xml"))) {
			return (localObject = (ViewResolver) this.a.get(paramString)).resolveViewName(paramString, paramLocale);
		}
		int i;
		String temp = "";
		if ((i = paramString.indexOf(":")) == -1) {
			localObject = a();
		} else {
			temp = paramString.substring(0, i);
			if (("redirect".equals(temp)) || ("forward".equals(temp))) {
				localObject = a();
			} else {
				localObject = (ViewResolver) this.a.get(temp);

				paramString = paramString.substring(i + 1);
			}
		}
		if (localObject != null) {
			return ((ViewResolver) localObject).resolveViewName(paramString, paramLocale);
		}
		return null;
	}

	private ViewResolver a() {
		return (ViewResolver) this.a.get(this.b);
	}
}