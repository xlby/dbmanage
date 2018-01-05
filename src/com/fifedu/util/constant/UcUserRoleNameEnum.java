package com.fifedu.util.constant;

/**
 * 枚举-用户中心人员角色
 *
 * @author super
 */
public enum UcUserRoleNameEnum {
    SCHOOLADMIN("schooladmin", "学校管理员"),
    EDUADMIN("eduadmin", "教育局管理员"),
    TEACHER("teacher", "教师"),
    STUDENT("student", "学生"),
    EDUPERSONNEL("edupersonnel", "教育局行政人员"),
    PARENT("parent", ""),
    SCHOOLPERSONNEL("schoolpersonnel", "学校行政人员"),
    SYSADMIN("sysadmin", "系统管理员"),
    SITEADMIN("siteadmin", "网站管理员"),
    SITEPERSONNEL("sitepersonnel", "网站行政人员");

    private String code;
    private String name;

    UcUserRoleNameEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (UcUserRoleNameEnum q : UcUserRoleNameEnum.values()) {
            if (code.equals(q.getCode())) {
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
