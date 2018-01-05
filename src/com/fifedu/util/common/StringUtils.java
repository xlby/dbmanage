package com.fifedu.util.common;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.iflytek.cycore.usercenter.api.TClass;
import com.iflytek.cycore.usercenter.api.AreaInfoSvc.AsyncProcessor.list_area_by_manager;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author tjx
 * @version 2013-12-30
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : str.toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	
	/**
	 * 字符串转换为Integer数组
	 * @param val 字符串
	 * @param regex 正则表达式
	 * @return
	 */
	public static Integer[] toIntegerArray(String val, String regex) {
		String[] valArr = val.split(regex);
		int arrLen = valArr.length;
		Integer[] it = new Integer[arrLen];
		for(int i=0; i < arrLen; i++) {
			it[i] = Integer.parseInt(valArr[i].trim());
		}
		return it;
	}
	
	/**
	 * 数组转成字符串
	 * <p>
	 * 可在打印日志的时候用
	 * </p>
	 * 
	 * @param args
	 * @return
	 */
	public static String argsToString(Object[] args){
		StringBuilder s = new StringBuilder("args:[");
		for(Object o : args){
			s.append(o).append(",");
		}
		s.append("]");
		
		return s.toString();
	}

	/**
	 * 判断是否为数字
	 * @param str
	 * @return
     */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0; ) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 返回字符串本身<p/>
	 * 如果是null 返回 空字符串
	 * @param obj
	 * @return
     */
	public static String ifNullDefault(Object obj){
		if(null != obj){
			return String.valueOf(obj);
		}else{
			return "";
		}
	}
	/**
	 * 将数组转换为string
	 * @param args 数字
	 * @param symbol 连接符
	 * @return
	 */
	public static String argsToString(Object[] args,String symbol){
		StringBuilder s = new StringBuilder();
		for(Object o : args){
			if(o.equals(args[args.length-1])){
				return s.append(o).toString();
			}
			s.append(o).append(symbol);
		}
		return s.toString();
	}
	public static String argsToList(List args,String symbol){
		StringBuilder s = new StringBuilder();
		for(Object o : args){
			if(o.equals(args.get(args.size()-1))){
				return s.append(o).toString();
			}
			s.append(o).append(symbol);
		}
		return s.toString();
	}
	/**
	 * 将平台返回的TClass 中的classId 提取
	 * @param args
	 * @param symbol
	 * @return
	 */
	public static String argsToListByIplatClassObj(List<TClass> args,String symbol){
		StringBuilder s = new StringBuilder();
		for(TClass o : args){
			if(o.getId().equals(args.get(args.size()-1).getId())){
				return s.append(o.getId()).toString();
			}
			s.append(o.getId()).append(symbol);
		}
		return s.toString();
	}
	
	
public static void main(String[] args) {
	String ss[]= {"44","22","ddd"}; 
	System.out.println(argsToString(ss,","));;
}
}
