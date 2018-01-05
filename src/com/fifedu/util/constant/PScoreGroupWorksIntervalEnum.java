package com.fifedu.util.constant;

/**
 * 枚举-作品审核状态
 * 
 * @author guqingxu
 *
 */
public enum PScoreGroupWorksIntervalEnum {
	COUNT_LOW60("count_low60","60分以下"),
	COUNT_LOW70("count_low70","60分~70分"),
	COUNT_LOW80("count_low80","70分~80分"),
	COUNT_LOW90("count_low90","80分~90分"),
	COUNT_LOW100("count_low100","90分~100分"),;

	private String code;
	private String name;

	PScoreGroupWorksIntervalEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(String code) {
		for (PScoreGroupWorksIntervalEnum q : PScoreGroupWorksIntervalEnum.values()) {
			if (q.getCode().equals(code)) {
				return q.name;
			}
		}
		return null;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

}
