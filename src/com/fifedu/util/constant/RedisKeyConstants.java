package com.fifedu.util.constant;

public class RedisKeyConstants {
    /**
     * Jedis缓存存放的KEY
     * */
	public static final String KYE_USER_INFO = "user_";
	public static final int CCB_DB_NUM = 0;
	public static final String CCB_PB_USER = "ccb_pb_user";  //存放用户信息
	public static final String CCB_STUDENT_PAPER_ANSWER = "ccb_student_paper_answer";  // 模考答案存储
	public static final String CCB_STUDENT_PACKAGE_ANSWER = "ccb_student_package_answer";  // 专练包答案存储
    /**
     * 保存冲刺宝学校和redisdbidx关系位置
     */
    public static final String CCB_SCHOOL_REDIS_DBIDX = "ccb_school_redis_dbidx";
	
/*	模考/专练学生答案
	模考试卷诊断报告
	单个专练包报告
	专练通关报告
    试卷的资源包存储路径	*/
	
	
	/************************************old begin***********************************************/
    
    
    public static String PB_USER_TEACHER = "pb_user_teacher";
    
    public static String PB_USER_STUDENT = "pb_user_student";
    public static String PB_TYPE_BANK = "pb_type_bank";
    public static String PB_FUNC = "pb_func";
    
    public static String PB_UNIT_INFO = "pb_unit_info";
    
    public static String PB_CLASS_INFO = "pb_class_info";
    
    public static String PB_COLLEGE_INFO = "pb_college_info";
    
    public static String PB_SCHOOL_INFO = "pb_school_info";
    
    //监考老师 后面再审查
    public static String SCENE_TEACHER = "scene_teacher";
    
    public static String PR_CLASS_INFO = "pr_class_info";
    //public static String PB_CCB_ROOM = "pb_CCB_room";
    
    //public static String PR_STUDENT_CLASS = "pr_student_class";
    
    
    public static final String CCB_PAPER = "CCB_paper";
    
    //阅卷老师
    public static String BATCH_TEACHER = "batch_teacher";
    //考试结果 考试id  与学生id，考场id的关系
    public static final String CCB_BATCH_RESULT = "CCB_batch_result";
    //考试规则
    public static final String CCB_RULE = "CCB_rule";
    
    //记录登录测试平台的用户数量
    public static final String SYS_TESTCENTER = "sys_testcenter";
    
    //单试卷阅卷进度表 个人答卷进度表
    public static final String CCB_CHECK_PROCESS = "CCB_check_process";
    
    //标记当前批次是否初始化了学生与班级的关系
    public static final String INIT_STUDENT_CLASS_FLAG = "init_student_class_flag";

    //保存试卷，以CCB_paper_content的形式保存对象。不拼接HTML
    public static final String CCB_PAPER_CONTENT = "CCB_paper_content";
    
  //用于记录 当前插入的数据的创建时间的，为以后数据清理提供时间依据
    public static final String REDIS_KEY_PERIOD = "redis_key_period";
    
    /**
     * 保存学校和redisdbidx关系位置
     */
    public static final String PR_SCHOOL_REDIS_DBIDX = "pr_school_redis_dbidx";
    
    //存储学生班级信息
    //public static final String PR_STUDENT_CLASS_KEY_LOGINNAME = "pr_student_class_key_loginname";
    
    /**
     * 考生答案缓存表 ，使用位置:
     *  1、开始考试缓存或获取考生答案信息
     *  2、答题后保存答案
     *  3、(学生主动或者系统提交)提交试卷后从缓存删除
     */
    public static final String CCB_CCBINEE_ANSWER_CACHE = "CCB_CCBinee_answer";
    /**
     * 考试试卷 音视频播放进度缓存
     */
    public static final String CCB_PAPER_AV_PROGRESS_CACHE = "CCB_paper_av_progress";
    
    /**
     * 考生答案缓存
     */
    public static final String CCB_PAPER_STANDARD_ANSWER_CACHE = "CCB_paper_standard_answer";
    /**
     * 在线阅卷缓存
     */
    public static final String ONLINE_CHECKING_CACHE = "CCB_online_checking";
    
    /**
     * 考试用的试卷缓存
     */
    public static final String CCB_PAPER_CACHE = "CCB_paper";
    
    /**
     * 试卷结构树缓存，存储 id name partnum parentid 即可
     */
    public static final String CCB_PAPER_TREE_CACHE = "CCB_paper_tree";
    
    /**
     * 考试试卷 以part形式缓存，方便答卷学生快速以part形式读取数据
     */
    public static final String CCB_PAPER_PART_CACHE = "CCB_paper_part";
    /**
     * 考试试卷： 只缓存改试卷的主观题部分
     */
    public static final String CCB_PAPER_SUBJECTIVITY_CACHE = "CCB_paper_subjectivity";
    
    /**
     * 考试试卷 以part json 形式缓存，方便答卷学生快速以part形式读取数据 ,并清除该数据中的 答案 等冗余字段的信息
     */
    public static final String CCB_PAPER_PART_JSON_CACHE = "CCB_paper_part_json_cache";
    

 
}
