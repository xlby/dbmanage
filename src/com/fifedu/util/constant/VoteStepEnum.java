package com.fifedu.util.constant;

/**
 * 枚举-投票环节有无
 * 
 * @author guqingxu
 *
 */
public enum VoteStepEnum {
	NO(0, "无"), YES(1, "有");

	private int code;
	private String name;

	VoteStepEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (VoteStepEnum q : VoteStepEnum.values()) {
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
