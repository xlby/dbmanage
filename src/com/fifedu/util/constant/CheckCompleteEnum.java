package com.fifedu.util.constant;

/**
 * 枚举-审核完成状况
 * 
 * @author guqingxu
 *
 */
public enum CheckCompleteEnum {
	NOTCHECK(0, "未审核"), FINISHED(1, "已审核");

	private int code;
	private String name;

	CheckCompleteEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (CheckCompleteEnum q : CheckCompleteEnum.values()) {
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
