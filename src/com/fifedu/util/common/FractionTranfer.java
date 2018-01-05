package com.fifedu.util.common;

public class FractionTranfer {
	
	/**35分制（听力、阅读）数组**/
	private static float[] fract_tf = {0,0.5f,1,1.5f,2,2.5f,3,3.5f,4,4.5f,5,5.5f,6,6.5f,7,7.5f,8,8.5f,9,9.5f,10,10.5f,11,11.5f,12,12.5f,13,13.5f,14,14.5f,15,15.5f,16,16.5f,17,17.5f,18,18.5f,19,19.5f,20,20.5f,21,21.5f,22,22.5f,23,23.5f,24,24.5f,25,25.5f,26,26.5f,27,27.5f,28,28.5f,29,29.5f,30,30.5f,31,31.5f,32,32.5f,33,33.5f,34,34.5f,35};
	private static float[] fract_tf_soz = {0,103,105,105,105,107,109,110,112,114,116,117,119,119,119,121,123,124,126,126,126,128,130,131,133,135,137,138,140,142,144,145,147,149,151,152,154,154,154,156,158,159,161,163,165,166,168,170,172,173,175,177,179,182,186,189,193,196,200,204,207,210,214,217,221,224,228,233,238,244,249};
	//private static Map<String,float> = ;
	/**15分制（写作、翻译）数组**/
	private static float[] fract_of = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	private static float[] fract_of_soz = {0,47,50,53,56,59,63,68,72,77,81,86,90,95,101,106};
	
	/**分值转换**/
	public static float getScore(int skillType,float score){
		float returnVal = 0;
		if(skillType == 1 || skillType == 3){//听力和阅读
			for (int i = 0; i < fract_tf.length; i++) {
				if(score == fract_tf[i]){
					returnVal = fract_tf_soz[i];
				}
			}
		}else if(skillType == 2 || skillType == 4){//写作和翻译
			for (int i = 0; i < fract_of.length; i++) {
				if(score == fract_of[i]){
					returnVal = fract_of_soz[i];
				}
			}
		}
		return returnVal;
	}
	
	
	public static void main(String[] args) {
		float score1 = getScore(1, 12.5f);
		float score2 = getScore(1, 12.5f);
		float score3 = getScore(1, 25);
		System.out.println("score1:"+score1);
		System.out.println("score2:"+score2);
		System.out.println("score3:"+score3);
		
		System.out.println(fract_tf.length+","+fract_tf_soz.length);
	}
	
	
	
}
