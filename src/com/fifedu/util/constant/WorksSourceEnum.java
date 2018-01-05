package com.fifedu.util.constant;

/**
 * 枚举-作品来源
 * 
 * @author guqingxu
 *
 */
public enum WorksSourceEnum {
	ORIGINAL(1, "原创"), REPRINT(2, "转载");

	private int code;
	private String name;

	WorksSourceEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (WorksSourceEnum q : WorksSourceEnum.values()) {
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
