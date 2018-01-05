package com.fifedu.util.constant;

/**
 * 枚举-活动状态
 * 
 * @author guqingxu
 *
 */
public enum EventStatusEnum {
	NOTSTARTED(1, "未开始"), ONGOING(2, "进行中"), CLOSED(3, "已关闭"), SAVED(4, "已存档");

	private int code;
	private String name;

	EventStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (EventStatusEnum q : EventStatusEnum.values()) {
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
