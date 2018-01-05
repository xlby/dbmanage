package com.fifedu.util.constant;

/**
 * 枚举-评分环节有无
 * 
 * @author guqingxu
 *
 */
public enum ScoreStepEnum {
	NO(0, "无"), YES(1, "有");

	private int code;
	private String name;

	ScoreStepEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (ScoreStepEnum q : ScoreStepEnum.values()) {
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
