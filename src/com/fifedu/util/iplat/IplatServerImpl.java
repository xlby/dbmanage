package com.fifedu.util.iplat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormat;

import com.fifedu.base.vo.OperResult;
import com.fifedu.util.http.HttpClientUtil;
import com.fifedu.util.json.JacksonUtil;

/**
 * 封装平台的接口函数
 * @author xlan
 *
 */
public class IplatServerImpl {

	
	public static Map<String, Object> loginUrlMap(String username,String password,String roleName){
		Map<String, Object> map = new HashMap<String, Object>();
		String url =  String.format(IplatUrlConfig.loginUrl, username, password ,roleName);
		String json= HttpClientUtil.httpGetRequest(url); // 获取平台地址响应信息
		map= JacksonUtil.jsonToObj(json, Map.class);
		return map;
	}
	/**
	 * // 获取用户个人信息
	 * @param sessionId
	 * @return
	 */
	public static String  getUserInfoUrl(String sessionId){
		
		return "";
	}
	
	/**
	 * 获取其他用户信息
	 * @return
	 */
	public static String getOtherUserInfoUrl(String userId,String sessionId){
		
		return "";
	}
	
	
	
}
