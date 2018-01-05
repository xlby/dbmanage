package com.fifedu.base.web.resolver;

import java.util.Locale;
import org.springframework.util.Assert;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class JsonViewResolver implements ViewResolver {
	private View a;

	public View getJsonView() {
		return this.a;
	}

	public void setJsonView(View paramView) {
		this.a = paramView;
	}

	public View resolveViewName(String paramString, Locale paramLocale) throws Exception {
		Assert.notNull(this.a, "'jsonView' must not be null");
		return this.a;
	}
}