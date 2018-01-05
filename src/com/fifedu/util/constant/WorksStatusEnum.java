package com.fifedu.util.constant;

/**
 * 枚举-作品可用状态
 * 
 * @author guqingxu
 *
 */
public enum WorksStatusEnum {
	NORMAL(1, "正常"), DISABLE(0, "销毁");

	private int code;
	private String name;

	WorksStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (WorksStatusEnum q : WorksStatusEnum.values()) {
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
