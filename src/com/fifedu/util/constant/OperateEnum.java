package com.fifedu.util.constant;

/**
 * 枚举-操作
 * 
 * @author guqingxu
 *
 */
public enum OperateEnum {
	SUBMIT(1, "作品提交"), EDIT(2, "后台编辑"), CHECKOK(3, "审核通过"), RETURN(4, "审核退回"), DISABLE(5, "销毁"), RECOVER(6, "恢复"), SAVE(7, "归档"), SCORE(8, "评分");

	private int code;
	private String name;

	OperateEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (OperateEnum q : OperateEnum.values()) {
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
