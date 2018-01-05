package com.fifedu.util.constant;

/**
 * 枚举-活动类型
 *
 * @author guqingxu
 */
public enum LoginRoleEnum {

    ADMIN("admin", "系统管理员","1"),
    EVENTPRINCIPAL("eventprincipal", "活动负责人","2"),
    CHECKER("checker", "审核人员","3"),
    RATINGPEOPLE("ratingpeople", "评分人员","4"),
    OTHER("other","其他人员","5");

    private String code;
    private String name;
    private String type;

    LoginRoleEnum(String code, String name,String type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    public static String getName(String code) {
        for (LoginRoleEnum q : LoginRoleEnum.values()) {
            if (q.getCode() == code) {
                return q.name;
            }
        }
        return null;
    }

    public static String getCodeByType(String type) {
        for (LoginRoleEnum q : LoginRoleEnum.values()) {
            if (q.getType().equals(type)) {
                return q.code;
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

    public String getType() {
        return type;
    }
}
