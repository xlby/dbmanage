package com.fifedu.util.constant;

/**
 * 枚举-活动类型
 * 
 * @author guqingxu
 *
 */
public enum EventTypeEnum {
	ALONE(1, "独立活动"), CONTEST(2, "赛事活动");

	private int code;
	private String name;

	EventTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (EventTypeEnum q : EventTypeEnum.values()) {
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
