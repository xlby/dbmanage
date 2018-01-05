package com.fifedu.util.report;

/**
 * 模考报告表，使用的工具类
 * @author lz
 *
 */
public class PaperReportUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * 通过得分率，得到得分率后方的文字描述
	 * 暂时写死
	 * 90%以上（含90%） 学霸
	 * 80%~90%（含80%） 良好
	 * 70%~80%（含70%） 中等
	 * 60%~70%（含60%）较弱
	 * 60%以下 薄弱
	 * @param averageRate
	 * @return
	 */
	public static String getQuestionTypeCdesc(String averageRate){
		Integer rateInteger = Integer.parseInt(averageRate);
		if(rateInteger<60){
			return "薄弱";
		}else if(rateInteger>=60&&rateInteger<70){
			return "较弱";
		}
		else if(rateInteger>=70&&rateInteger<80){
			return "中等";
		}else if(rateInteger>=80&&rateInteger<90){
			return "良好";
		}else if(rateInteger>=90){
			return "学霸";
		}else {
			return "没判断_averageRate";
		}
	}
	
	/**
	 * 通过题型ID，判断题型是否默认隐藏
	 * 默认隐藏的题型：
	 * b5115eb449a14e4297868c3b7f492c2b  写作题型
	 * 3f74840c7e7443f4892460e742384a43   翻译
	 * @param averageRate
	 * @return
	 */
	public static Integer getQuestionTypeIsDefaultShow(String questionTypeId){
		if("b5115eb449a14e4297868c3b7f492c2b"==questionTypeId||"3f74840c7e7443f4892460e742384a43"==questionTypeId||"b5115eb449a14e4297868c3b7f492c2b".equals(questionTypeId)||"3f74840c7e7443f4892460e742384a43".equals(questionTypeId)){
			return 0;
		}
		return 1;
	}

}
