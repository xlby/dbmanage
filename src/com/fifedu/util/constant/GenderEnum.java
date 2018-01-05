package com.fifedu.util.constant;

/**
 * 枚举-性别
 * 
 * @author guqingxu
 *
 */
public enum GenderEnum {
	MALE(1, "男"), FEMALE(0, "女");

	private int code;
	private String name;

	GenderEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (GenderEnum q : GenderEnum.values()) {
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
