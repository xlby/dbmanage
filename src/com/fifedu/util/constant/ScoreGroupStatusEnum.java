package com.fifedu.util.constant;

/**
 * 枚举-评分组可评状态
 * 
 * @author guqingxu
 *
 */
public enum ScoreGroupStatusEnum {
	OPEN(1, "开启"), CLOSE(0, "关闭");

	private int code;
	private String name;

	ScoreGroupStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (ScoreGroupStatusEnum q : ScoreGroupStatusEnum.values()) {
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
