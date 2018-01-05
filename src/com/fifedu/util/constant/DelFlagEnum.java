package com.fifedu.util.constant;

/**
 * 枚举-删除标记
 * 
 * @author guqingxu
 *
 */
public enum DelFlagEnum {
	NORMAL(0, "正常"), DELETED(1, "已删除");

	private int code;
	private String name;

	DelFlagEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (DelFlagEnum q : DelFlagEnum.values()) {
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
