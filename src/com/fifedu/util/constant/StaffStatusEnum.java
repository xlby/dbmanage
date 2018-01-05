package com.fifedu.util.constant;

/**
 * 枚举-工作人员状态
 * 
 * @author guqingxu
 *
 */
public enum StaffStatusEnum {
	NORMAL(1, "正常"), DISABLE(0, "禁用");

	private int code;
	private String name;

	StaffStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (StaffStatusEnum q : StaffStatusEnum.values()) {
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
