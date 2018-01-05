package com.fifedu.util.scoreMath;
/**
 * 分数计算
 * @author lz
 *
 */
public class ScoreMathUtil {

	public static void main(String[] args) {
		Float aFloat = mathScore(59.5f,100f,710f);
		System.out.println(aFloat);
	}
	
	/**
	 * 分数转换，例：studentScore=60,totalScore=100,originalScore=710, 学生得分60，试卷的标准分为100，要转换为710的分数值
	 * @param studentScore 学生得分
	 * @param totalScore  时间总分
	 * @param originalScore 要转换的分值的总分
	 * @return
	 */
	public static Float mathScore(Float studentScore,Float totalScore,Float originalScore){
		Float socreFloat = studentScore*(originalScore/totalScore);
		return socreFloat;
	}
}
