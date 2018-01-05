package com.fifedu.util.constant;

import com.ctakit.common.util.Resources;


/**
 * 文件名：SystemConstants.java
 * 用于存放系统常用常量
 */
public class SystemConstants {
	
    public static class System {
	/***
	 * 域名
	 */
	public static String DOMAIN = Resources.getString("sys.domain","itest.com");

    }

    
    /**
     * UPLOAD_USERPIC用于存放系统账号信息对应的图片路径
     * */
    public static String UPLOAD_USERPIC = "upload_userPic";
    
    /**
     * SYS_APPIDX用于存放系统上传附件目录
     * */
    public static String SYS_APPIDX = "sys_appidx";
    
    /**
     * UPLOAD_USERPIC用于存放系统账号信息对应的图片路径
     * */
    public static String UPLOAD_ANSWER_MEDIA = "upload_answer_media";
    public static String STATIC_URL = "static_url";
    
    /**
     * 数据库配置表  考试异序试卷总份数
     */
    public static String DB_CFG_EXAM_PAPER_COUNT = "exam_paper_count";
    
 
    /**
     * CLASSEXAM_RULE用于存放班级考试时试题是否异序开关
     * */
    public static String CLASSEXAM_RULE = "classexam_rule";

    /**
     * 考卷题号默认从1开始
     * */
    public static int   QUESTIONNUM = 1;
    
    //题型分类(技能)
    /**
     * 听
     */
    public static final String SKILL_LISTEN="0";
    /**
     * 说
     */
    public static final String SKILL_SPEAK="1";
    /**
     * 读
     */
    public static final String SKILL_READ="2";
    /**
     * 写
     */
    public static final String SKILL_WRITE="3";

    /**
     * 综合--似乎是  ： 译
     */
    public static final String SKILL_TRANSLATE ="4";
    
    
    //成绩分布率
    public static final String   EXCELLENT ="excellent";
    
    public static final String   GOOD ="good";
    
    public static final String   MEDIUM ="medium";
    
    public static final String   PASSING ="passing";
    
    public static final String   FAIL ="fail";
    
    //===========================pangpanlong add start============================
    //考生剩余时间统计任务Map的常量key
    public static final String STUDENT_EXAM_REMAIN_TIME="student_exam_remain_time";
    

    //===========================pangpanlong add end============================
    //=============================zrd  add start===============================
    //*******************************用户角色编码(用户角色类型)*******************************
    /**
     * 测试系统普通老师
     */
    public static String  ROLE_COMMON_TEACHER= "03";//测试数据，待统一！！！！！！！！！！
    /**
     * 测试系统负责人
     */
    public static String  ROLE_LEADER_TEACHER= "02";//测试数据，待统一！！！！！！！！！！
    //*******************************系统Id*******************************
    /**
     * 用户中心系统id
     */
    public static String SYS_TYPE_USERCENTER = "0";
    /**
     * 测试系统id
     */
    public static String SYS_TYPE_FIFTEST = "1";
    /**
     * 出题系统id
     */
    public static String SYS_TYPE_FIFQBANK = "2";
    //=============================zrd  add start===============================
    
    //添加系统常用变量
    public static final String SESSION_OBJ_USER = "user";
    
    /**
     * 分隔符分号 ；
     */
    public static final String SEPARATOR_SEMICOLON = ";";
    
    
    /**
     * 分隔符逗号 ，
     */
    public static final String SEPARATOR_COMMA = ",";
    
    /**
     * 分隔符冒号 ：
     */
    public static final String SEPARATOR_COLON = ":";
    
    
    /*** 角色***********************************************/
    /**
     * 角色： 出题负责人
     */
    public static final String ROLE_EN_NAME_TBADMIN = "tbadmin";
    /**
     * 角色： 测试负责人
     */
    public static final String ROLE_EN_NAME_TCADMIN = "tcadmin";
    /**
     * 角色： 管理员
     */
    public static final String ROLE_EN_NAME_EDUADMIN = "eduadmin";
    /**
     * 角色： 测试教师 和 出题教师
     */
    public static final String ROLE_EN_NAME_TEACHER = "teacher";
    /**
     * 角色： 学生
     */
    public static final String ROLE_EN_NAME_STUDENT = "student";
    
    /**
     * 角色： 学校领导
     */
    public static final String ROLE_EN_NAME_EDULEADER = "eduleader";
    
    
    
    /**
     * 角色： 管理员
     */
    public static final String ROLE_CODE_EDUADMIN = "01";
    /**
     * 角色： 测试负责人
     */
    public static final String ROLE_CODE_TCADMIN = "02";
    /**
     * 角色： 出题负责人
     */
    public static final String ROLE_CODE_TBADMIN = "04";
    /**
     * 角色： 测试教师 和 出题教师
     */
    public static final String ROLE_CODE_TEACHER = "05";
    /**
     * 角色： 学生
     */
    public static final String ROLE_CODE_STUDENT = "06";
    /**
     * 角色： 学校领导
     */
    public static final String ROLE_CODE_EDULEADER = "09";
    
    
    /*** 用户类型 ***********************************************/
    public static final String USER_TYPE_STUDENT = "0";
    public static final String USER_TYPE_TEACHER = "1";
    public static final String USER_TYPE_ADMIN = "2";
    
    /**
     * 本地用户或者统一用户
     */
    public static final boolean IS_CYCORE = true;
    
    /**
     * 学校虚拟考场ID
     */
    public static final String VIRTUAL_EXAM_ROOM_ID = "999999";
    public static final Integer VIRTUAL_EXAM_ROOM_SEAT_NUM = 50000;
    public static final String VIRTUAL_EXAM_ROOM_NAME = "FiF默认考场";
    
    /**
     * 配置文件常量
     */
    //app code
    public static final String APP_CFG_APP_CODE = "app_code";
    

    /**
     * 平台对接 应用 code
     */
    public static String appCode = "";
    
	/**
	 * 缓存同步状态，正在同步中为true，否则为FALSE
	 */
    public static boolean cacheSyncFlag = false;
    
    /**
     * 补考 异常  缺考 迟到 存储 key 
     */
    public static  final String KEY_MAKEUP = "key_makeup";
    
    /**
     * 考试模式  1 后台  2 前台 临时
     */
    public static  final String EXAM_CLIENT_MODE = "exam_client_mode";
    
    /**
     * 用户是否能使用自动阅卷功能？1：可以 0：不可以
     */
    public static  final String AUTO_CHECK = "auto_check";
    
    /**
     * 口语答题进度
     */
	public static final String ORALSCHEDULE = "oralschedule";
    
	/**
     * 数据库数据已删除（无效）
     */
	public static final Integer DB_DATA_DELETE = 1;
	
	/**
     * 数据库数据未删除（有效）
     */
	public static final Integer DB_DATA_NOT_DELETE = 0;
    
	/**
     * 参加考试状态：0--正常 
     */
	public static final Integer DB_DATA_NOT_STATUS = 0;
	
	/**
     * 参加考试状态：1--缺考  
     */
	public static final Integer DB_DATA_STATUS = 1;

	public static final String STUDENT_EXAM_INFO = "student_exam_info";

	/**
	 * 作业系统学生答题信息表
	 */
	public static final String STUDENT_HOMEWORK_INFO = "student_homework_info";
	
	/**
	 * 用于每十分钟扫描学生信息进行保存的表
	 */
	public static final String STUDENT_RESULT_INFO = "student_result_info";
	
}
