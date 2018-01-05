package com.fifedu.util.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuestionTypeConstant {

    /**
     * 短对话
     */
    public static String SHORT_DIALOGUE		  = "018080923d9f735a013e1ae9bc1217e7";
    /**
     * 复合式听写
     */
    public static String COMPOUND_DICTATION	      = "018080923d9f735a013e1b13555617f1";
    /**
     * 快速阅读
     */
    public static String SKIMMING_AND_SCANNING	   = "018080923d9f735a013e1b242d6a17f5";
    /**
     * 仔细阅读
     */
    public static String READING_COMPREHENSION	   = "018080923d9f735a013e1b4fa63617f8";
    /**
     * 完型填空
     */
    public static String CLOZE			   = "018080923d9f735a013e1b5d734e1800";
    /**
     * 改错
     */
    public static String CORRECTION		      = "018080923d9f735a013e1b6130001803";
    /**
     * 短文写作
     */
    public static String WRITING			 = "018080923d9f735a013e1b66cc541805";
    /**
     * 翻译
     */
    public static String TRANSLATION		     = "018080923d9f735a013e1b8e561c1809";
    /**
     * 半句翻译
     */
    public static String TRANSLATION_HALF_SENTENCE		     = "c325d5377d8e4525a58c6d08bb2fb530";
    
    /**
     * 单词听写
     */
    public static String WORDDICTATION		   = "018080923d9f735a013e20cf66eb19ee";
    /**
     * 句子听写
     */
    public static String SENTENCE_DICTATION	      = "018080923d9f735a013e20d246bb19f1";

    /**
     * 语法与词汇
     */
    public static String GRAMMAR_AND_VOCABULARY	  = "03fefe98d16d471ba13b7fa091661ceb";

    /**
     * 长对话
     */
    public static String LONG_DIALOGUE		   = "018080923d9f735a013e86c9a65a308c";

    /**
     * 长对话
     */
    public static String LONG_DIALOGUE_SUB3	      = "018080923f123ce9013f42ef284d1285";

    /**
     * 听力复合题，子题带音频
     */
    public static String COMPLEX_LISTENING_SUB_HAVE_VIDEO = "115290a6a77f4415b1dc6ab3a33174a5";
    
    /**
     * 段落翻译（英译中）
     */
    public static String PARAGRAPH_TRANSLATION_E2C  = "8d43351a563b4c888c05739bdd023b31";
    
    /**
     * 段落翻译（中译英）
     */
    public static String PARAGRAPH_TRANSLATION_C2E  = "5c3e8f41d6604308910e963f7d892be1";
    
    /**
     * 翻译段落中的5个句子(英译中)
     */
    public static String PARAGRAPH_TRANSLATION_E2C5_SENTENCE  = "0af94d1bc0c1426688501c83e0cfb1ac";
    
    /**
     * 阅读文章后回答问题
     */
    public static String ANSWER_QUESTIONS_AFTER_READING  = "6bc8c0dc01d14543867e9cbbebb13245";
    /******************************** 复合体子题类型归类 ************************************************/
    /**
     * 带音频单选子题，不显示题干
     */
    public static String CHILD_NO_SINGLE_CHOICE_VIDEO = "bc12993390d94797a651865ce018fc33";
    /**
     * 带音频单选子题，显示题干
     */
    public static String CHILD_STEM_SINGLE_CHOICE_VIDEO = "af0fbe6cc25c4b17b4b7fb48c5a7184c";
    
    /**
     * 填空题
     */
    public static String CHILD_FILL_BLANK		= "ff8080812382dbb101238357169f0016";

    /**
     * 无题干单选题（不需要添加题目说明）(4选1) 适合于完型填空子题
     */
    public static String CHILD_NO_SINGLE_CHOICE_IN_4     = "01808092245168f9012451a86ab5004d";
    /**
     * 有题干单选题(4选1)
     */
    public static String CHILD_STEM_SINGLE_CHOICE_IN_4   = "ff8080812382d6eb012382d90e5e0001";

    /**
     * 有题干单选(3选1)
     */
    public static String CHILD_STEM_SINGLE_CHOICE_IN_3   = "402899c52fce1e8e012fcefe9ec4004d";

    /**
     * 有题干多选题(4选多)
     */
    public static String CHILD_STEM_MULTIPLE_CHOICE_IN_4 = "ff8080812382d6eb012382da5aac0003";
    /**
     * 改错子题类型 （专八改错子题10空）
     */
    public static String CHILD_CORRECTION_PROFESSIONAL_8 = "ff8080813173b4f701320ef0cf591504";

    public static Map<String, String> getQuestionTypeMap() {
	Map<String, String> questionTypeMap = new HashMap<String, String>();
	questionTypeMap.put(WORDDICTATION, "单词听写");
	questionTypeMap.put(COMPOUND_DICTATION, "复合式听写");
	questionTypeMap.put(SENTENCE_DICTATION, "句子听写");
	questionTypeMap.put(SHORT_DIALOGUE, "短对话");
	questionTypeMap.put(TRANSLATION, "翻译");
	questionTypeMap.put(WRITING, "短文写作");
	questionTypeMap.put(SHORT_DIALOGUE, "短对话");
	questionTypeMap.put(SKIMMING_AND_SCANNING, "快速阅读");
	questionTypeMap.put(READING_COMPREHENSION, "仔细阅读");
	questionTypeMap.put(CLOZE, "完型填空");
	questionTypeMap.put(CORRECTION, "改错");
	questionTypeMap.put(GRAMMAR_AND_VOCABULARY, "语法与词汇");
	questionTypeMap.put(LONG_DIALOGUE, "长对话-4子题");
	questionTypeMap.put(LONG_DIALOGUE_SUB3, "长对话-3子题");
	return questionTypeMap;
    }

    public static List<SelectOption> getQuestionTypeList() {
	List<SelectOption> questionTypeList = new ArrayList<SelectOption>();
	questionTypeList.add(new SelectOption(WORDDICTATION, "单词听写"));
	questionTypeList.add(new SelectOption(SENTENCE_DICTATION, "句子听写"));
	questionTypeList.add(new SelectOption(GRAMMAR_AND_VOCABULARY, "语法与词汇"));
	questionTypeList.add(new SelectOption(LONG_DIALOGUE_SUB3, "长对话-3子题"));
	
	return questionTypeList;
    }

    public static String getTypeNameByCode(String typeCode) {
	Map<String, String> questionTypeMap = getQuestionTypeMap();
	return questionTypeMap.get(typeCode);
    }
}
