package com.fifedu.util.common;

import java.util.Random;

/**
 * key值算法
 * @author lz
 *
 */
public class KeyStatForSkill {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String answerString = "dddddddddddddddddddddddddddddddddddddddddddddddd";
		Float skillScore=0f;
		float s = keyStatForAnswer(answerString,skillScore);
     
        System.out.println(s);
	}

	
	

	public static Float keyStatForAnswer(String answer,Float lastSkillScore){
		if(null==answer||"".equals(answer)){
			return 0f;
		}
		Integer answerCount = answer.length();
		if(answerCount>0&&answerCount<=20){
			return getScore(1,3);
		}
		if(answerCount>20){
			if(lastSkillScore>0&&lastSkillScore<=10){
				return getScore(1,3);
			}
			if(lastSkillScore>10&&lastSkillScore<=20){
				return getScore(3,7);
			}
			if(lastSkillScore>20&&lastSkillScore<=35){
				return getScore(7,10);
			}
			else{
				return getScore(1,3);
			}
		}
		
	   return 0f;	
	}
	
	/**
	 * 获取区间随机数
	 * @param max
	 * @param min
	 * @return
	 */
	public static float getScore(int min,int max){
		   Random random = new Random();
	       int s = random.nextInt(max)%(max-min+1) + min;
	       return s;
	}
}
