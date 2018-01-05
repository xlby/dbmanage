package com.fifedu.util.constant;

/**
 * 枚举-资源类型
 * 
 * @author guqingxu
 *
 */
public enum ResTypeEnum {
	ARTICLE(1, "文章"), DOC(2, "文档"), VIDEO(3, "视频"), PIC(4, "图片"), AUDIO(5, "音频"), COMPLEX(6, "综合");

	private int code;
	private String name;

	ResTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(int code) {
		for (ResTypeEnum q : ResTypeEnum.values()) {
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
