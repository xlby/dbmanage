package com.fifedu.util.constant;

/**
 * 枚举-报名环节有无
 * 
 * @author guqingxu
 *
 */
public enum RegStepEnum {
	NO(0, "无"), YES(1, "有");

	private int code;
	private String name;

	RegStepEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (RegStepEnum q : RegStepEnum.values()) {
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
