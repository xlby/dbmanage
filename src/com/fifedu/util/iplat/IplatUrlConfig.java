package com.fifedu.util.iplat;

import com.fifedu.util.core.ThreadLocalUtils;

/**
 * 平台提供接口定义地址
 * @author lz
 *
 */
public class IplatUrlConfig {

	//public static String serverLoginUrl = "http://test.fifedu.com/iplat";
	//public static String serverLoginUrl = "http://www.fifedu.com:80/iplat";
	public static String serverLoginUrl = (String)ThreadLocalUtils.getRequest().getSession().getServletContext().getAttribute("iplatServerPath");
	
	 public static String loginUrl = serverLoginUrl
	            + "/kyxl/account/login_ccb?username=%1$s&password=%2$s&roleName=%3$s"; // 登录，少了个最关键的
	 //public static String loginUrl = serverLoginUrl
	 //           + "/kyxl/account/login_?username=%1$s&password=%2$s&roleName=student";  or teacher // 登录，少了个最关键的
	 // testuser1: fifedu001  123456 student    testuser2: fifedu1001  123456 teacher    
	 //http://test.fifedu.com/iplat/kyxl/account/login_?username=fifedu001&password=123456&roleName=student
	 // http://www.fifedu.com:80/iplat/kyxl/account/login_?username=fifedu001&password=123456&roleName=student
	
	// 获取用户个人信息
    public static String getUserInfoUrl = serverLoginUrl
            + "/kyxl/userinfo/findUser";// 参数?sessionId=%1$s";// 获取用户信息
    
    public static String getOtherUserInfoUrl = serverLoginUrl
            + "/kyxl/userinfo/findUser?sessionId=%1$s&userId=%2$s";// 获取其他用户信息

    public static String editUserHeadPortraitUrl = serverLoginUrl
            + "/kyxl/userinfo/editUser?sessionId=%1$s&pic=%2$s";// 更新用户信息(头像)
    public static String editUserNickName = serverLoginUrl
            + "/kyxl/userinfo/editUser?sessionId=%1$s&nickname=%2$s";// 更新用户信息(昵称)
    public static String editUserGender = serverLoginUrl
            + "/kyxl/userinfo/editUser?sessionId=%1$s&sex=%2$s";// 更新用户信息(性别)
    public static String editUserBirthDay = serverLoginUrl
            + "/kyxl/userinfo/editUser?sessionId=%1$s&birthday=%2$d";// 更新用户信息(出生年月)
    public static String editUserPsw = serverLoginUrl
            + "/kyxl/account/editPassword?sessionId=%1$s&oldPassword=%2$s&newPassword=%3$s";// 修改用户密码
    public static String uploadHeadPortraitUrl = serverLoginUrl
            + "/kyxl/userinfo/upload?";// 上传用户头像

    public static String getValidCodeUrl = serverLoginUrl
            + "/kyxl/account/genValidCode?type=%1$d&mobile=%2$s";// 获取验证码

    public static String isUsernameMatchMobileUrl = serverLoginUrl
            + "/kyxl/account/isUsernameMatchMobile?username=%1$s&mobile=%2$s"; // 判断用户名和手机号是否匹配
    
    public static String isValidCodeCorrectUrl = serverLoginUrl
            + "/kyxl/account/isValidCodeCorrect?username=%1$s&mobile=%2$s&validCode=%3$s"; // 判断验证码是否正确

    public static String resetUserPswUrl = serverLoginUrl
            + "/kyxl/account/resetPassword?username=%1$s&mobile=%2$s&validCode=%3$s&newPassword=%4$s";// 忘记密码后，重新设置密码

    public static String bindPhoneUrl = serverLoginUrl 
            + "/kyxl/account/editUserMobile?sessionId=%1$s&mobile=%2$s&validCode=%3$s&password=%4$s"; // 绑定手机
}
