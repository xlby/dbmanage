package com.fifedu.util.constant;

/**
 * 枚举-作品模块类型
 * 
 * @author guqingxu
 *
 */
public enum WorksModuleTypeEnum {
	CONTENT(1, "内容模块"), RESOURCE(2, "资源模块");

	private int code;
	private String name;

	WorksModuleTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (WorksModuleTypeEnum q : WorksModuleTypeEnum.values()) {
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
