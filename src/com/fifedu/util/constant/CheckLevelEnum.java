package com.fifedu.util.constant;

/**
 * 枚举-审核级别
 * 
 * @author guqingxu
 *
 */
public enum CheckLevelEnum {
	ONE(1, "一级"), TWO(2, "二级"), THREE(3, "三级");

	private int code;
	private String name;

	CheckLevelEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (CheckLevelEnum q : CheckLevelEnum.values()) {
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
