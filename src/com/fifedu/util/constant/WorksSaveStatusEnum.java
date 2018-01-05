package com.fifedu.util.constant;

/**
 * 枚举-作品归档状态
 * 
 * @author guqingxu
 *
 */
public enum WorksSaveStatusEnum {
	SAVED(1, "已归档"), NOSAVE(0, "未归档");

	private int code;
	private String name;

	WorksSaveStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (WorksSaveStatusEnum q : WorksSaveStatusEnum.values()) {
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
