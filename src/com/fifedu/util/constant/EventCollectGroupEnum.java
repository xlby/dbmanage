package com.fifedu.util.constant;

/**
 * 枚举-活动征集对象
 * 
 * @author guqingxu
 *
 */
public enum EventCollectGroupEnum {
	ALL(1, "全体"), FILTER(2, "筛选");

	private int code;
	private String name;

	EventCollectGroupEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (EventCollectGroupEnum q : EventCollectGroupEnum.values()) {
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
