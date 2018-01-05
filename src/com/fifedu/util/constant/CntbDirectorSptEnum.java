package com.fifedu.util.constant;

/**
 * 枚举-投稿指导人支持
 * 
 * @author guqingxu
 *
 */
public enum CntbDirectorSptEnum {
	YES(1, "是"), NO(0, "否");

	private int code;
	private String name;

	CntbDirectorSptEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (CntbDirectorSptEnum q : CntbDirectorSptEnum.values()) {
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
