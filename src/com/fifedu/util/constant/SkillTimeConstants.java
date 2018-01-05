package com.fifedu.util.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangtse
 * 试卷及技能时长常量类
 * **/
public class SkillTimeConstants {
	
	/**听力时长**/
	public static int LISTEN = 1800;
	/**写作时长**/
	public static int WRITE = 2400;
	/**阅读时长**/
	public static int READ = 1800;
	/**翻译时长**/
	public static int TRANSLATE = 2400;
	
	/**技能名称所对应的时长Map**/
	public static Map<String, Integer> getSkillTime(){
		Map<String, Integer> skillMap = new HashMap<String, Integer>();
		skillMap.put("听力", LISTEN);
		skillMap.put("写作", WRITE);
		skillMap.put("阅读", READ);
		skillMap.put("翻译", TRANSLATE);
		return skillMap;
	}
	
	/**根据技能名称获取时长**/
	public static int getTimeBySkillName(String skillName){
		int skillTime = 0;
		Map<String, Integer> skillMap = getSkillTime();
		skillTime = skillMap.get(skillName);
		return skillTime;
	}
	
	
	public static int getTimeBySubScript(int num){
		int skillTime = 0;
		switch (num) {
		case 0:
			skillTime = LISTEN;
			break;
		case 1:
			skillTime = WRITE;
			break;
		case 2:
			skillTime = READ;
			break;
		case 3:
			skillTime = TRANSLATE;
			break;
		default:
			break;
		}
		
		return skillTime;
	}
	

	/**试卷总时长**/
	public static int getPaperTime(){
		return LISTEN+WRITE+READ+TRANSLATE;
	}

}
