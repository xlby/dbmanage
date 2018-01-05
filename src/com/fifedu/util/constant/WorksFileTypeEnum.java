package com.fifedu.util.constant;

/**
 * 枚举-作品文件类型
 * 
 * @author guqingxu
 *
 */
public enum WorksFileTypeEnum {
	WORKS(1, "作品文件"), MODULE(2, "模块文件"), ATTACH(3, "附件文件"),COVER(4, "封面图文件"),EDITOR(5, "富文本编辑器上传的文件"),OTHER(6, "其他文件");

	private int code;
	private String name;

	WorksFileTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (WorksFileTypeEnum q : WorksFileTypeEnum.values()) {
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
