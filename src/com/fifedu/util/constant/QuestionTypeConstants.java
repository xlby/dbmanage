package com.fifedu.util.constant;

import java.util.HashMap;
import java.util.Map;

/**题型工具类***/
public class QuestionTypeConstants {
	
	/*******************************父级题型*******************************************/
	/**短篇新闻**/
	public static String SHORT_NEWS = "a5ee7382fe9747b884e0a8c03b9a92e4";
	
	/**讲座/讲话**/
	public static String LECTURE_SPEECH  = "950380bca3b344ed97503e97516100b8";
	
	/**短对话**/
	public static String SHORT_DIALOGUE = "372c2cb245184707a68c53f270a6f083";
	
	
	/**长对话**/
	public static String LONG_DIALOGUE = "ba8157c4653344b48d6343ec9ee04c78";
	
	
	/**听力篇章**/
	public static String LISTENING_CHAPTER = "c1b69eff7b124ec19adfae6f5b2b9285";
	
	
	/**短文听写**/
	public static String PASSAGE_DICTATION = "05f9e7f0bec942ba8843a4516dceca8a";
	
	
	/**词汇理解**/
	public static String LEXICAL_COMPREHENSION = "96abcaa16ddf4beebe20ffef0a757146";
	
	
	/**长篇阅读**/
	public static String LONG_READING = "63639d4ca4874651abe9a61948af77bb";
	
	/**仔细阅读**/
	public static String READ_CAREFULLY = "446cf2cc2a7f419aa685ea64b46b4726";
	
	/**汉译英**/
	public static String TRANSLATION = "3f74840c7e7443f4892460e742384a43";
	
	/**写作**/
	public static String WRITING = "b5115eb449a14e4297868c3b7f492c2b";
	
	
	/*******************************子题型*******************************************/
	/**四级新闻听力2子题（4选1无题干有音频）**/
	public static String FOUR_HEARING_NEWS_TOPIC_2 = "f1d3fd1ab8824110a9c5c9909540ff1c";
	
	/**四级新闻听力3子题（4选1无题干有音频）**/
	public static String FOUR_HEARING_NEWS_TOPIC_3 = "821b834c2e2c426281b82831b4411ec1";
	
	/**六级讲座听力3子题（4选1无题干有音频 ）**/
	public static String SIX_LISTENING_TOPIC_3  = "cc5970bc17dc453891819fa86c9a65cf";
	
	/**六级讲座听力4子题（4选1无题干有音频 ）**/
	public static String SIX_LISTENING_TOPIC_4 = "a124e4e04551482eb30755a46f651d54";
	
	/**四六级短对话1子题（4选1无题干无音频）**/
	public static String CET_SHORT_DIALOGUE_TOPIC_1 = "cef3bc13aef940dd8495375998c44148";
	
	/**四六级长对话（3小题）**/
	public static String CET_LONG_DIALOGUE_TOPIC_3 = "0dd993cfac644bacab444146d9afe7e8";
	
	/**四六级长对话（4小题）**/
	public static String CET_LONG_DIALOGUE_TOPIC_4 = "a7cd421975224f64b3a43ed5e1dd23a8";
	
	/**四六级短文理解听力（3小题）**/
	public static String CET_PASSAGES_LISTENING_TOPIC_3 = "8a0656a37e0448129c5eea562a24d400";
	
	/**四六级短文理解听力（4小题）**/
	public static String CET_PASSAGES_LISTENING_TOPIC_4 = "2e707408591640f0abbd75816bfdf84d";
	
	/**四六级听写（10空）3遍**/
	public static String CET_DICTATION_TOPIC_10 = "cc608ed072a74c6f935f23094f075dcb";
	
	/**选词填空（10空，有选词框）**/
	public static String BANKED_CLOZE_TOPIC_10 = "c8e5732416994464884dfd7ce73a2b42";
	
	/**四六级配对题10子题（1空）**/
	public static String CET_MATCHING_TOPIC_10 = "7ac8c0dc01d14543867e9cbbebb13267";
	
	/**阅读理解5子题（4选1显题干）**/
	public static String READING_COMPREHENSION_TOPIC_5 = "018080923d9f735a013e1b4fa63617f8";
	
	/**四六级-中译英-段落翻译（1文本域）**/
	public static String PARAGRAPH_TRANSLATION  = "5c3e8f41d6604308910e963f7d892be1";
	
	/**作文（1文本域）**/
	public static String COMPOSITION = "018080923d9f735a013e1b66cc541805";
	
	
	
	
	/*******************************父级题型数组*******************************************/
	/**短篇新闻**/
	public static String [] shortNews = {FOUR_HEARING_NEWS_TOPIC_2,FOUR_HEARING_NEWS_TOPIC_3};
	
	/**讲座/讲话**/
	public static String [] lectureSpeech  = {SIX_LISTENING_TOPIC_3,SIX_LISTENING_TOPIC_4};
	
	/**短对话**/
	public static String [] shortDialogue = {CET_SHORT_DIALOGUE_TOPIC_1};
	
	
	/**长对话**/
	public static String [] longDialogue = {CET_LONG_DIALOGUE_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_4};
	
	
	/**听力篇章**/
	public static String [] listeningChapter = {CET_PASSAGES_LISTENING_TOPIC_3,CET_PASSAGES_LISTENING_TOPIC_4};
	
	
	/**短文听写**/
	public static String [] passageDictation = {CET_DICTATION_TOPIC_10};
	
	
	/**词汇理解**/
	public static String [] lexicalComprehension = {BANKED_CLOZE_TOPIC_10};
	
	
	/**长篇阅读**/
	public static String [] longReading = {CET_MATCHING_TOPIC_10};
	
	/**仔细阅读**/
	public static String [] readCarefully = {READING_COMPREHENSION_TOPIC_5};
	
	/**汉译英**/
	public static String [] translations = {PARAGRAPH_TRANSLATION};
	
	/**写作**/
	public static String [] compositions = {COMPOSITION};
	
	
	
	/***技能下的父级题型数组：父级题型按技能归类数组******************************************************************************************/
	//听力
	public static String [] listenByParentQuestionTypeId = {SHORT_NEWS,LECTURE_SPEECH,SHORT_DIALOGUE,LONG_DIALOGUE,LISTENING_CHAPTER,PASSAGE_DICTATION};
	//写作
	public static String [] writeByParentQuestionTypeId = {WRITING};
	//阅读
	public static String [] readByParentQuestionTypeId = {LEXICAL_COMPREHENSION,LONG_READING,READ_CAREFULLY};
	//翻译
	public static String [] translateByParentQuestionTypeId = {TRANSLATION};
	
	/***技能下的子题型数组：子题型按技能归类数组******************************************************************************************/
	//听力
	public static String [] listenByQuestionTypeId = {FOUR_HEARING_NEWS_TOPIC_2,FOUR_HEARING_NEWS_TOPIC_3,SIX_LISTENING_TOPIC_3,SIX_LISTENING_TOPIC_4,CET_SHORT_DIALOGUE_TOPIC_1,CET_LONG_DIALOGUE_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_4,CET_PASSAGES_LISTENING_TOPIC_3,CET_PASSAGES_LISTENING_TOPIC_4,CET_DICTATION_TOPIC_10};
	//写作
	public static String [] writeByQuestionTypeId = {COMPOSITION};
	//阅读
	public static String [] readByQuestionTypeId = {BANKED_CLOZE_TOPIC_10,CET_MATCHING_TOPIC_10,READING_COMPREHENSION_TOPIC_5};
	//翻译
	public static String [] translateByQuestionTypeId = {PARAGRAPH_TRANSLATION};
	
	
	
	
	
	/**四级题型顺序**/
	public static String [] cet4 = {FOUR_HEARING_NEWS_TOPIC_2,FOUR_HEARING_NEWS_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_4,CET_PASSAGES_LISTENING_TOPIC_3,CET_PASSAGES_LISTENING_TOPIC_4,COMPOSITION,BANKED_CLOZE_TOPIC_10,CET_MATCHING_TOPIC_10,READING_COMPREHENSION_TOPIC_5,PARAGRAPH_TRANSLATION};
	
	/**六级题型顺序**/
	public static String [] cet6 = {CET_LONG_DIALOGUE_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_4,CET_PASSAGES_LISTENING_TOPIC_3,CET_PASSAGES_LISTENING_TOPIC_4,SIX_LISTENING_TOPIC_3,SIX_LISTENING_TOPIC_4,COMPOSITION,BANKED_CLOZE_TOPIC_10,CET_MATCHING_TOPIC_10,READING_COMPREHENSION_TOPIC_5,PARAGRAPH_TRANSLATION};
	
	/**巅峰冲刺题型顺序**/
	public static String [] sprint = {CET_SHORT_DIALOGUE_TOPIC_1,CET_LONG_DIALOGUE_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_4,CET_PASSAGES_LISTENING_TOPIC_3,CET_PASSAGES_LISTENING_TOPIC_4,CET_DICTATION_TOPIC_10,COMPOSITION,BANKED_CLOZE_TOPIC_10,CET_MATCHING_TOPIC_10,READING_COMPREHENSION_TOPIC_5,PARAGRAPH_TRANSLATION};
	
	
	
	/**四级试卷听写阅译Map(1.听力，2.写作，3.阅读，4.翻译)**/
	public static String[] getCET4SkillMap(int skillType) {
		String [] listen = {FOUR_HEARING_NEWS_TOPIC_2,FOUR_HEARING_NEWS_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_4,CET_PASSAGES_LISTENING_TOPIC_3,CET_PASSAGES_LISTENING_TOPIC_4};
		String [] write = {COMPOSITION};
		String [] read = {BANKED_CLOZE_TOPIC_10,CET_MATCHING_TOPIC_10,READING_COMPREHENSION_TOPIC_5};
		String [] translate = {PARAGRAPH_TRANSLATION};
		Map<Integer, String[]> skillMap = new HashMap<>();
		skillMap.put(1, listen);
		skillMap.put(2, write);
		skillMap.put(3, read);
		skillMap.put(4, translate);
		
		String [] cet4 = skillMap.get(skillType);
		
		return cet4;
	}
	
	/**六级试卷听写阅译Map(1.听力，2.写作，3.阅读，4.翻译)**/
	public static String[] getCET6SkillMap(int skillType) {
		String [] listen = {CET_LONG_DIALOGUE_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_4,CET_PASSAGES_LISTENING_TOPIC_3,CET_PASSAGES_LISTENING_TOPIC_4,SIX_LISTENING_TOPIC_3,SIX_LISTENING_TOPIC_4};
		String [] write = {COMPOSITION};
		String [] read = {BANKED_CLOZE_TOPIC_10,CET_MATCHING_TOPIC_10,READING_COMPREHENSION_TOPIC_5};
		String [] translate = {PARAGRAPH_TRANSLATION};
		Map<Integer, String[]> skillMap = new HashMap<>();
		skillMap.put(1, listen);
		skillMap.put(2, write);
		skillMap.put(3, read);
		skillMap.put(4, translate);
		
		String [] cet6 = skillMap.get(skillType);
		
		return cet6;
	}
	
	/**巅峰冲刺试卷听写阅译Map(1.听力，2.写作，3.阅读，4.翻译)**/
	public static String[] getSprintSkillMap(int skillType) {
		String [] listen = {CET_SHORT_DIALOGUE_TOPIC_1,CET_LONG_DIALOGUE_TOPIC_3,CET_LONG_DIALOGUE_TOPIC_4,CET_PASSAGES_LISTENING_TOPIC_3,CET_PASSAGES_LISTENING_TOPIC_4,CET_DICTATION_TOPIC_10};
		String [] write = {COMPOSITION};
		String [] read = {BANKED_CLOZE_TOPIC_10,CET_MATCHING_TOPIC_10,READING_COMPREHENSION_TOPIC_5};
		String [] translate = {PARAGRAPH_TRANSLATION};
		Map<Integer, String[]> skillMap = new HashMap<>();
		skillMap.put(1, listen);
		skillMap.put(2, write);
		skillMap.put(3, read);
		skillMap.put(4, translate);
		
		String [] sprint = skillMap.get(skillType);
		
		return sprint;
	}
	
	
	
	/**根据父级题型id获取题型id数组**/
	public static String [] getQusetionTypeByParent(String parentId){
		Map<String, String[]> parentMap = new HashMap<>();
		parentMap.put(SHORT_NEWS, shortNews);
		parentMap.put(LECTURE_SPEECH, lectureSpeech);
		parentMap.put(SHORT_DIALOGUE, shortDialogue);
		parentMap.put(LONG_DIALOGUE, longDialogue);
		parentMap.put(LISTENING_CHAPTER, listeningChapter);
		parentMap.put(PASSAGE_DICTATION, passageDictation);
		parentMap.put(LEXICAL_COMPREHENSION, lexicalComprehension);
		parentMap.put(LONG_READING, longReading);
		parentMap.put(READ_CAREFULLY, readCarefully);
		parentMap.put(TRANSLATION, translations);
		parentMap.put(WRITING, compositions);
		
		String [] questionTypeArr = parentMap.get(parentId);
		return questionTypeArr;
	}
	
	
	
	
	
	 public static Map<String, String> getQuestionTypeMap() {
			Map<String, String> questionTypeMap = new HashMap<String, String>();
			questionTypeMap.put(SHORT_NEWS, "短篇新闻");
			questionTypeMap.put(LECTURE_SPEECH, "讲座/讲话");
			questionTypeMap.put(SHORT_DIALOGUE, "短对话");
			questionTypeMap.put(LONG_DIALOGUE, "长对话");
			questionTypeMap.put(LISTENING_CHAPTER, "听力篇章");
			questionTypeMap.put(PASSAGE_DICTATION, "短文听写");
			questionTypeMap.put(LEXICAL_COMPREHENSION, "词汇理解");
			questionTypeMap.put(LONG_READING, "长篇阅读");
			questionTypeMap.put(READ_CAREFULLY, "仔细阅读");
			questionTypeMap.put(TRANSLATION, "汉译英");
			questionTypeMap.put(WRITING, "写作");
			
			questionTypeMap.put(FOUR_HEARING_NEWS_TOPIC_2, "四级新闻听力2子题（4选1无题干有音频）");
			questionTypeMap.put(FOUR_HEARING_NEWS_TOPIC_3, "四级新闻听力3子题（4选1无题干有音频）");
			questionTypeMap.put(SIX_LISTENING_TOPIC_3, "六级讲座听力3子题（4选1无题干有音频 ）");
			questionTypeMap.put(SIX_LISTENING_TOPIC_4, "六级讲座听力4子题（4选1无题干有音频 ）");
			questionTypeMap.put(CET_SHORT_DIALOGUE_TOPIC_1, "四六级短对话1子题（4选1无题干无音频）");
			questionTypeMap.put(CET_LONG_DIALOGUE_TOPIC_3, "四六级长对话（3小题）");
			questionTypeMap.put(CET_LONG_DIALOGUE_TOPIC_4, "四六级长对话（4小题）");
			questionTypeMap.put(CET_PASSAGES_LISTENING_TOPIC_3, "四六级短文理解听力（3小题）");
			questionTypeMap.put(CET_PASSAGES_LISTENING_TOPIC_4, "四六级短文理解听力（4小题）");
			questionTypeMap.put(CET_DICTATION_TOPIC_10, "四六级听写（10空）3遍");
			questionTypeMap.put(BANKED_CLOZE_TOPIC_10, "选词填空（10空，有选词框）");
			questionTypeMap.put(CET_MATCHING_TOPIC_10, "四六级配对题10子题（1空）");
			questionTypeMap.put(READING_COMPREHENSION_TOPIC_5, "阅读理解5子题（4选1显题干）");
			questionTypeMap.put(PARAGRAPH_TRANSLATION, "四六级-中译英-段落翻译（1文本域）");
			questionTypeMap.put(COMPOSITION, "作文（1文本域）");
			return questionTypeMap;
	 }
	
	
	 /**根据题型code获取到题型名称**/
	 public static String getTypeNameByCode(String typeCode) {
		Map<String, String> questionTypeMap = getQuestionTypeMap();
		return questionTypeMap.get(typeCode);
    }
	
	
	
	
	/**根据题型id获取到题型名称**/
	public static String getNameById(String id){
		
		if (SHORT_NEWS.equals(id)) {
			return "短篇新闻";
		}else if(LECTURE_SPEECH.equals(id)){
			return "讲座/讲话";
		}else if(SHORT_DIALOGUE.equals(id)){
			return "短对话";
		}else if(LONG_DIALOGUE.equals(id)){
			return "长对话";
		}else if(LISTENING_CHAPTER.equals(id)){
			return "听力篇章";
		}else if(PASSAGE_DICTATION.equals(id)){
			return "短文听写";
		}else if(LEXICAL_COMPREHENSION.equals(id)){
			return "词汇理解";
		}else if(LONG_READING.equals(id)){
			return "长篇阅读";
		}else if(READ_CAREFULLY.equals(id)){
			return "仔细阅读";
		}else if(TRANSLATION.equals(id)){
			return "汉译英";
		}else if(WRITING.equals(id)){
			return "写作";
			
		}else if(FOUR_HEARING_NEWS_TOPIC_2.equals(id)){
			return "四级新闻听力2子题（4选1无题干有音频）";
		}else if(FOUR_HEARING_NEWS_TOPIC_3.equals(id)){
			return "四级新闻听力3子题（4选1无题干有音频）";
		}else if(SIX_LISTENING_TOPIC_3.equals(id)){
			return "六级讲座听力3子题（4选1无题干有音频 ）";
		}else if(SIX_LISTENING_TOPIC_4.equals(id)){
			return "六级讲座听力4子题（4选1无题干有音频 ）";
		}else if(CET_SHORT_DIALOGUE_TOPIC_1.equals(id)){
			return "四六级短对话1子题（4选1无题干无音频）";
		}else if(CET_LONG_DIALOGUE_TOPIC_3.equals(id)){
			return "四六级长对话（3小题）";
		}else if(CET_LONG_DIALOGUE_TOPIC_4.equals(id)){
			return "四六级长对话（4小题）";
		}else if(CET_PASSAGES_LISTENING_TOPIC_3.equals(id)){
			return "四六级短文理解听力（3小题）";
		}else if(CET_PASSAGES_LISTENING_TOPIC_4.equals(id)){
			return "四六级短文理解听力（4小题）";
		}else if(CET_DICTATION_TOPIC_10.equals(id)){
			return "四六级听写（10空）3遍";
		}else if(BANKED_CLOZE_TOPIC_10.equals(id)){
			return "选词填空（10空，有选词框）";
		}else if(CET_MATCHING_TOPIC_10.equals(id)){
			return "四六级配对题10子题（1空）";
		}else if(READING_COMPREHENSION_TOPIC_5.equals(id)){
			return "阅读理解5子题（4选1显题干）";
		}else if(PARAGRAPH_TRANSLATION.equals(id)){
			return "四六级-中译英-段落翻译（1文本域）";
		}else if(COMPOSITION.equals(id)){
			return "作文（1文本域）";
		}else{
			return null;
		}
	}
	
	
	
	/**根据题型id获取到父级题型id**/
	public static String getParentIdByQuestionId(String id){
		if(FOUR_HEARING_NEWS_TOPIC_2.equals(id) || FOUR_HEARING_NEWS_TOPIC_3.equals(id)){
			return SHORT_NEWS;
		}else if(SIX_LISTENING_TOPIC_3.equals(id) || SIX_LISTENING_TOPIC_4.equals(id)){
			return LECTURE_SPEECH;
		}else if(CET_SHORT_DIALOGUE_TOPIC_1.equals(id)){
			return SHORT_DIALOGUE;
		}else if(CET_LONG_DIALOGUE_TOPIC_3.equals(id) || CET_LONG_DIALOGUE_TOPIC_4.equals(id)){
			return LONG_DIALOGUE;
		}else if(CET_PASSAGES_LISTENING_TOPIC_3.equals(id) || CET_PASSAGES_LISTENING_TOPIC_4.equals(id)){
			return LISTENING_CHAPTER;
		}else if(CET_DICTATION_TOPIC_10.equals(id)){
			return PASSAGE_DICTATION;
		}else if(BANKED_CLOZE_TOPIC_10.equals(id)){
			return LEXICAL_COMPREHENSION;
		}else if(CET_MATCHING_TOPIC_10.equals(id)){
			return LONG_READING;
		}else if(READING_COMPREHENSION_TOPIC_5.equals(id)){
			return READ_CAREFULLY;
		}else if(PARAGRAPH_TRANSLATION.equals(id)){
			return TRANSLATION;
		}else if(COMPOSITION.equals(id)){
			return WRITING;
		}else{
			return "";
		}
	}
	
	
	/**根据父级题型id获取到技能类型**/
	public static Integer getSkillIdByParentQuestionId(String id){
		if (SHORT_NEWS.equals(id) || LECTURE_SPEECH.equals(id) || SHORT_DIALOGUE.equals(id) || LONG_DIALOGUE.equals(id) || LISTENING_CHAPTER.equals(id) || PASSAGE_DICTATION.equals(id)) {
			return 1;
		}else if(LEXICAL_COMPREHENSION.equals(id) || LONG_READING.equals(id) || READ_CAREFULLY.equals(id)){
			return 3;
		}else if(TRANSLATION.equals(id)){
			return 4;
		}else if(WRITING.equals(id)){
			return 2;
		}else{
			return null;
		}
	}
	
	
	
	/**根据子题型id获取到技能类型**/
	public static Integer getSkillIdByParentQuestionTypeId(String questionTypeId){
		if (getResultFlag(listenByParentQuestionTypeId, questionTypeId)) {
			return 1;
		}else if(getResultFlag(writeByParentQuestionTypeId, questionTypeId)){
			return 2;
		}else if(getResultFlag(readByParentQuestionTypeId, questionTypeId)){
			return 3;
		}else if(getResultFlag(translateByParentQuestionTypeId, questionTypeId)){
			return 4;
		}else{
			return null;
		}
	}
	
	
	
	/**根据子题型id获取到技能类型**/
	public static Integer getSkillIdByQuestionTypeId(String questionTypeId){
		if (getResultFlag(listenByQuestionTypeId, questionTypeId)) {
			return 1;
		}else if(getResultFlag(writeByQuestionTypeId, questionTypeId)){
			return 2;
		}else if(getResultFlag(readByQuestionTypeId, questionTypeId)){
			return 3;
		}else if(getResultFlag(translateByQuestionTypeId, questionTypeId)){
			return 4;
		}else{
			return null;
		}
	}
	
	/**判断是否存在题型id***/
	public static boolean getResultFlag(String [] arr,String id){
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			if (id.equals(arr[i])) {
				flag = true;
			}
		}
		return flag;
	}
	
	
	
	
	
	/**判断是否为写作或翻译题型**/
	public static boolean isTranslationAndWriting(String parentQuestionTypeId){
		boolean flag = false;
		if (TRANSLATION.equals(parentQuestionTypeId) || WRITING.equals(parentQuestionTypeId)) {
			flag = true;
		}
		return flag;
	}
	
	/**判断是否为听写或选词填空题型**/
	public static boolean isDictationAndBlanks(String parentQuestionTypeId){
		boolean flag = false;
		if (PASSAGE_DICTATION .equals(parentQuestionTypeId) || LEXICAL_COMPREHENSION .equals(parentQuestionTypeId)) {
			flag = true;
		}
		return flag;
	}
	
	
	/**返回值为0：写作或翻译题型，其余返回值为1**/
	public static int isComposites(String questionTypeId){
		int num = 1;
		if (COMPOSITION.equals(questionTypeId) || PARAGRAPH_TRANSLATION.equals(questionTypeId)) {
			num = 0;
		}
		return num;
	}

	/**判断是否为听力选择题(短篇新闻、短对话、长对话、听力篇章、讲座/讲话)**/
	public static boolean listening(String parentQuestionTypeId){
		boolean flag = false;
		if (SHORT_NEWS.equals(parentQuestionTypeId) || LECTURE_SPEECH.equals(parentQuestionTypeId) || SHORT_DIALOGUE.equals(parentQuestionTypeId) || LONG_DIALOGUE.equals(parentQuestionTypeId) || LISTENING_CHAPTER.equals(parentQuestionTypeId)) {
			flag = true;
		}
		return flag;
	}

}
