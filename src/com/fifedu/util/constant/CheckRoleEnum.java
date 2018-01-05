package com.fifedu.util.constant;

/**
 * 枚举-审核担当
 * 
 * @author guqingxu
 *
 */
public enum CheckRoleEnum {
	FIRST(1, "初审"), REVIEW(2, "复审"), LAST(3, "终审");

	private int code;
	private String name;

	CheckRoleEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (CheckRoleEnum q : CheckRoleEnum.values()) {
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
