package com.fifedu.util.constant;

import com.ctakit.common.util.Resources;

public class Constants {
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static String SYSTEM_CHARSET = Resources.getString("charset",
			"UTF-8");
	public static String DEFAULT_WATERMARK = "sys.default.watermark";
	public static String IMAGE_SIZE = "sys.image.size";

	public class Application {
		public static final String BASE_URL = "sys.url.baseURL";
		public static final String IMG_URL = "sys.url.imgURL";
		public static final String STATIC_URL = "sys.url.staticURL";
		public static final String STATIC_REV_RELEASE = "sys.static.revRelease";
	}

	public class Cookie {
		public static final String USER_NAME = "userName";
		public static final String REMEMBER = "remember";
		public static final String SESSIONID = "sid";
		public static final String PASSWORD = "password";
		public static final String DOMAIN = "domain";
		public int EXPIRES = 0;
	}

	public class Request {
		public static final String POST = "POST";
		public static final String GET = "GET";
		public static final String ERRORS = "errors";
		public static final String ERROR = "error";
		public static final String PAGE = "page";
		public static final String MESSAGE = "message";
		public static final String MODEL_REQUEST_ATRRIBUTE = "sys.model.request.atrribute";
	}

	public class Session {
		public static final String AUTHORIZEDUSER = "authorizedUser";
	}

}
