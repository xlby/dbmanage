package com.fifedu.util.constant;

/**
 * 枚举-工作人员类型
 * 
 * @author guqingxu
 *
 */
public enum StaffTypeEnum {
	SYSADMIN(1, "系统管理员"), EVENTADMIN(2, "活动负责人"), CHECK(3, "审核人员"), SCORE(4, "评分人员"), OTHER(5, "其他人员");

	private int code;
	private String name;

	StaffTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (StaffTypeEnum q : StaffTypeEnum.values()) {
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
