package com.fifedu.util.constant;

/**
 * 枚举-作品审核状态
 * 
 * @author guqingxu
 *
 */
public enum CheckStatusEnum {
	NOTCHECK(1, "未审核"), FIRSTEND(2, "初审完"), REVIEWEND(3, "复审完"), LASTEND(4, "终审完"), RETURN(5, "退回");

	private int code;
	private String name;

	CheckStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (CheckStatusEnum q : CheckStatusEnum.values()) {
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
