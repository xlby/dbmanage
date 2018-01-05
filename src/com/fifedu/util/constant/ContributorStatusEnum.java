package com.fifedu.util.constant;

/**
 * 枚举-投稿人状态
 * 
 * @author guqingxu
 *
 */
public enum ContributorStatusEnum {
	NORMAL(1, "正常"), DISABLE(0, "禁用");

	private int code;
	private String name;

	ContributorStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (ContributorStatusEnum q : ContributorStatusEnum.values()) {
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
