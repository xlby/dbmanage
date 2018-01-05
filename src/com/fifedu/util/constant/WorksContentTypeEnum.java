package com.fifedu.util.constant;

/**
 * 枚举-作品内容类型
 * 
 * @author guqingxu
 *
 */
public enum WorksContentTypeEnum {
	WORKS(1, "作品内容"), MODULE(2, "模块内容");

	private int code;
	private String name;

	WorksContentTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (WorksContentTypeEnum q : WorksContentTypeEnum.values()) {
			if (q.getCode() == code) {
				return q.name;
			}
		}
		return null;
	}

	public String getName() {
		return this.name;
	}

	public int getCode() {
		return this.code;
	}

}
