package com.fifedu.util.constant;

/**
 * 枚举-活动组织范围
 * 
 * @author guqingxu
 *
 */
public enum EventScopeEnum {
	ALL(1, "全站"), LOCAL(2, "地方");

	private int code;
	private String name;

	EventScopeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (EventScopeEnum q : EventScopeEnum.values()) {
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
