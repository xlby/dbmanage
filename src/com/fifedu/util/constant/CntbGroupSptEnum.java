package com.fifedu.util.constant;

/**
 * 枚举-投稿分组支持
 * 
 * @author guqingxu
 *
 */
public enum CntbGroupSptEnum {
	YES(1, "是"), NO(0, "否");

	private int code;
	private String name;

	CntbGroupSptEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (CntbGroupSptEnum q : CntbGroupSptEnum.values()) {
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
