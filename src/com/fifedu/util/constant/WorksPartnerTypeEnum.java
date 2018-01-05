package com.fifedu.util.constant;

/**
 * 枚举-作品关联人类型
 * 
 * @author guqingxu
 *
 */
public enum WorksPartnerTypeEnum {
	AUTHOR(1, "扩展作者"), DIRECTOR(2, "指导人");

	private int code;
	private String name;

	WorksPartnerTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (WorksPartnerTypeEnum q : WorksPartnerTypeEnum.values()) {
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
