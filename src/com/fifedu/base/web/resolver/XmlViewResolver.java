package com.fifedu.base.web.resolver;

import java.util.Locale;
import org.springframework.util.Assert;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class XmlViewResolver implements ViewResolver {
	private View a;

	public View getXmlView() {
		return this.a;
	}

	public void setXmlView(View paramView) {
		this.a = paramView;
	}

	public View resolveViewName(String paramString, Locale paramLocale) throws Exception {
		Assert.notNull(this.a, "'xmlView' must not be null");
		return this.a;
	}
}