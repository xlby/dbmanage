package com.fifedu.util.json;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaperAnalysisUtil {
	
	/**记录指定字符串出现次数**/
	private static int counter = 0;
	/**切割次数**/
	private static int splitTime = 0;
	
	/**strong标签-开始**/
	public static String STRONG_FIRST = "<strong>";
	/**strong标签-结束**/
	public static String STRONG_LAST = "</strong>";
	/**p标签-开始**/
	public static String P_FIRST = "<p>";
	/**p标签-结束**/
	public static String P_LAST = "</p>";
	/**br标签-开始**/
	public static String BR_FIRST = "<br>";
	/**br标签-结束**/
	public static String BR_LAST = "</br>";
	/**br标签-自封闭式结束**/
	public static String BR_LAST_AFTER = "<br/>";
	/**标题存放临时变量**/
	public static String TLE = "";
	
	/**临时存储数组变量**/
	private static String [] analysisArr = null;
	
	/**字母表数组**/
	public static String[] alphabet = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };
	/**切割选项条件**/
	public static String splitRegex_letter = "A\\.|B\\.|C\\.|D\\.|E\\.|F\\.|G\\.|H\\.|I\\.|J\\.|K\\.|L\\.|M\\.|N\\.|O\\.|P\\.|Q\\.|R\\.|S\\.|T\\.|U\\.|V\\.|W\\.|X\\.|Y\\.|Z\\.";
	public static String splitRegex_brck = "A\\)|B\\)|C\\)|D\\)|E\\)|F\\)|G\\)|H\\)|I\\)|J\\)|K\\)|L\\)|M\\)|N\\)|O\\)|P\\)|Q\\)|R\\)|S\\)|T\\)|U\\)|V\\)|W\\)|X\\)|Y\\)|Z\\)";
	public static String splitReg = "1\\.|2\\.|3\\.|4\\.|5\\.|6\\.|7\\.|8\\.|9\\.|10\\.|11\\.|12\\.|13\\.|14\\.|15\\.|16\\.|17\\.|18\\.|19\\.|20\\.";
	/**字母加点的查找条件**/
	public static String [] sxpt = {"A.","B.","C.","D.","E.","F.","G.","H.","I.","J.","K.","L.","M.","N.","O.","P.","Q.","R.","S.","T.","U.","V.","W.","X.","Y.","Z."};
	/**字母加中括号的查找条件**/
	public static String [] smbt = {"[A]","[B]","[C]","[D]","[E]","[F]","[G]","[H]","[I]","[J]","[K]","[L]","[M]","[N]","[O]","[P]","[Q]","[R]","[S]","[T]","[U]","[V]","[W]","[X]","[Y]","[Z]"};
	/**字母加右括号的查找条件**/
	public static String [] slbt = {"A)","B)","C)","D)","E)","F)","G)","H)","I)","J)","K)","L)","M)","N)","O)","P)","Q)","R)","S)","T)","U)","V)","W)","X)","Y)","Z)"};
	
	/**图片格式**/
	public static String []  imgArr= {"jpg","jpeg","gif","png","bmp"};
	/**图片格式标记**/
	public static String imgFlag = ""; 
	
	
	/**将解析切割成数组存放**/
	public static String[] getAnalysisArr(String analysis){
		//查找是否存在p标签，不存在则以数字来切割解析
		if (analysis.indexOf(P_FIRST) == -1) {
			//定义切割字符串后临时存储的数组
			String [] temp = analysis.split(splitReg);
			//定义返回数组，长度为临时数组的长度减一
			analysisArr = new String[temp.length-1];
			for (int i = 0; i < analysisArr.length; i++) {
				analysisArr[i] = temp[i+1].trim();
			}
			
		}else{//以p标签的格式解析
			int time = stringNumbers(getReplaceAllStr(analysis),P_LAST);
			analysisArr = new String[time];
			counter = 0;
			getReplaceStrArr(analysis, P_FIRST, P_LAST);
			splitTime = 0;
		}
		return analysisArr;
	}
	
	
	/**查找是否存在图片**/
	public static boolean findImage(String str){
		boolean flag = false;
		for (int i = 0; i < imgArr.length; i++) {
			//判断是否存在图片
			if (str.indexOf(imgArr[i]) != -1 || str.indexOf(imgArr[i].toUpperCase()) != -1) {
				//存在则返回true，并返回图片的格式
				flag = true;
				imgFlag = imgArr[i];
				break;
			}
		}
		return flag;
	}
	
	
	
	/**将选项字符切割成数组，并拼接成ABCD选项形式返回**/
	public static String[] getSplitStr(String str){
		String [] arr = str.split("@!@");
		for (int i = 0; i < arr.length; i++) {
			//arr[i] = alphabet[i]+". "+arr[i].trim();
			arr[i] = alphabet[i]+". "+getOptions(arr[i]);
		}
		return arr;
	}
	
	/**将选项字符切割成数组，并拼去掉首尾空格**/
	public static String[] getSplitStrArr(String answer){
		String [] arr = answer.split("@!@");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		}
		return arr;
	}
	
	
	/**去除空格和<br>**/
	public static String getOptions(String str){
		String option = "";
		if(str != null && str != ""){
			option = str;
			int num = option.indexOf(BR_LAST_AFTER);
			if(num != -1){
				option = option.replaceAll(BR_LAST_AFTER, "");
			}
			option = option.trim();
		}
		return option;
	}
	
	
	/**标题挖空——将听写、挖空题字符串替换成下划线加题号，例如：__(36)__**/
	public static String getReplaceStr(String str,String regexFirst,String regexLast,int num){
		//转标签
		String strArr = getReplaceAllStr(str);
		StringBuffer sb = new StringBuffer(strArr);

		//统计指定字符的数量
		int count = stringNumbers(str,regexFirst);
		counter=0;//清空全局变量
		for (int i = 0; i < count; i++) {
			//记录开始标签第一次出现位置
			int start = sb.indexOf(regexFirst);
			//从开始标签的位置查找结束标签的位置
			int tempNum = sb.indexOf(regexLast, start);
			//记录结束标签第一次出现位置（结束标签的位置加上自身长度）
			int end = tempNum+regexLast.length();
			//
			sb.replace(start, end, "__("+(num+i+1)+")__");
		}
		//StringBuffer转String
		String title = sb.toString();
		//去掉字符串中的strong标签
		getRemoveLable(title, "<strong", ">");
		title = TLE;
		title = title.replaceAll(STRONG_FIRST, "");
		title = title.replaceAll(STRONG_LAST, "");
		return title;
	}
	
	
	/**递归调用去除指定的标签**/
	public static void getRemoveLable(String title,String regStart,String regEnd){
		if(title.indexOf(regStart) != -1){
			StringBuffer sb = new StringBuffer(title);//定义StringBuffer
			int start = sb.indexOf(regStart);//获取标签开始位置
			int end = sb.indexOf(regEnd, start)+regEnd.length();//获取标签结束位置
			sb.replace(start, end, "");//将标签替换为空字符
			getRemoveLable(sb.toString(),regStart,regEnd);//重复调用自身
		}else{
			TLE = title;
		}
	}
	
	
	
	/**解析填空——将听写、挖空题字符串替换成下题号加答案(36)yangtse**/
	public static String getReplaceStrNo(String str,String answer,String regexFirst,String regexLast,int num){
		//答案切割成数组
		String [] arr = getSplitStrArr(answer);
		//字符编码转标签
		String strArr = getReplaceAllStr(str);
		StringBuffer sb = new StringBuffer(strArr);
		//统计指定字符的数量
		int count = stringNumbers(str,regexFirst);
		counter=0;//清空全局变量
		for (int i = 0; i < count; i++) {
			//记录开始标签第一次出现位置
			int start = sb.indexOf(regexFirst);
			//从开始标签的位置查找结束标签的位置
			int tempNum = sb.indexOf(regexLast, start);
			//记录结束标签第一次出现位置（结束标签的位置加上自身长度）
			int end = tempNum+regexLast.length();
			//截取并替换成题号+答案内容（例如：36<strong>yangtse</strong>）
			sb.replace(start, end, "("+(num+i+1)+")"+arr[i]);
		}
		return sb.toString();
	}
	
	
	
	/**选词填空题（切割解析成数组）**/
	public static int getReplaceStrArr(String str,String regexFirst,String regexLast){
		String strArr = getReplaceAllStr(str);
		if (str.indexOf(regexLast) == -1) {
			return 0;
		}else if (str.indexOf(regexLast) != -1) {
			int start = strArr.indexOf(regexFirst)+regexFirst.length();
			int end = strArr.indexOf(regexLast);
			String nextStr = strArr.substring(end+regexLast.length());
			String currentStr = strArr.substring(start, end);
			analysisArr[splitTime] = currentStr.trim();
			splitTime++;
			getReplaceStrArr(nextStr,regexFirst,regexLast);
			return splitTime;
		}
		return 0;
	}
	
	
	
	/**统计个数指定字符在整个字符串中的数量(需处理的字符串,切割条件)**/
	public static int stringNumbers(String title,String regex){
		String str = getReplaceAllStr(title);
		if (str.indexOf(regex)==-1) {
			return 0;
		}else if (str.indexOf(regex)!=-1) {
			counter++;
			stringNumbers(str.substring(str.indexOf(regex)+regex.length()),regex);
			return counter;
		}
		return 0;
	}
	
	/**字符编码转标签**/
	public static String getReplaceAllStr(String str){
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&amp;#39;", "'");
		str = str.replaceAll("&amp;nbsp;", " ");
		str = str.replaceAll("&amp;quot;", "\"");
		str = str.replaceAll("&nbsp;", " ");
		int num = str.indexOf(BR_LAST_AFTER);
		if(num != -1){
			str = str.replaceAll(BR_LAST_AFTER, "");
		}
		return str;
	}
	
	/**将答案按照选项字母顺序拼接（字母+答案）**/
	public static Map<String, String[]> getAnswerStrSort(String answer,String bankStr,String [] bankArr){
		Map<String, String[]> answerMap = new HashMap<String, String[]>();
		//答案顺序
		String [] answerSortArr = getSplitStrArr(answer);
		//回传答案数组内容
		String [] answerArrNew = new String[answerSortArr.length];
		//回传答案数组下标（在选项中的答案下标）
		String [] answerSubScript = new String[answerSortArr.length];
		//判断选项前字母拼接类型
		int point = bankStr.indexOf("A.");
		int brack = bankStr.indexOf("A)");
		if (point != -1) {
			//用答案去找在选项中的顺序，然后把查找到的下标的数值赋值给答案
			for (int i = 0; i < answerSortArr.length; i++) {
				String answerArr = answerSortArr[i].toLowerCase();
				for (int j = 0; j < bankArr.length; j++) {
					String answerBank = bankArr[j].toLowerCase();
					if (answerArr.equals(answerBank)) {
						answerArrNew[i] = alphabet[j]+". "+answerSortArr[i];
						answerSubScript[i] = j+"";
					}
				}
			}
			answerMap.put("answer_tle", answerArrNew);
			answerMap.put("answer_sub", answerSubScript);
		}else{
			if (brack != -1) {
				//用答案去找在选项中的顺序，然后把查找到的下标的数值赋值给答案
				for (int i = 0; i < answerSortArr.length; i++) {
					for (int j = 0; j < bankArr.length; j++) {
						if (answerSortArr[i].equals(bankArr[j])) {
							answerArrNew[i] = alphabet[j]+") "+answerSortArr[i];
							answerSubScript[i] = j+"";
						}
					}
				}
				answerMap.put("answer_tle", answerArrNew);
				answerMap.put("answer_sub", answerSubScript);
			}
		}
		
		return answerMap;
	}
	
	
	/**把选项解析成数组（字母+选项）**/
	public static Map<String, String[]> getAnswerOptionArr(String bankStr){
		Map<String, String[]> answerMap = new HashMap<String, String[]>();
		
		int point = bankStr.indexOf("A.");
		int brack = bankStr.indexOf("A)");
		if (point != -1) {
			//根据字母切割获取到选项数组
			String[] splitStr = bankStr.split(splitRegex_letter);
			//字母加选项数组
			String [] bankSortArr = new String[splitStr.length-1];
			//只有选项数组
			String [] bankArr = new String[splitStr.length-1];
			for (int i = 0; i < bankSortArr.length; i++) {
				
				String repStr = getReplaceStr(splitStr[i+1]);
				//字母加选项（去除空格和空白字符）
				bankSortArr[i] = alphabet[i] + ". " + repStr;
				//只有选项（去除空格和空白字符）
				bankArr[i] = repStr;
			}
			//加字母的答案选项
			answerMap.put("option_zm", bankSortArr);
			//未加字母的答案选项
			answerMap.put("option_kb", bankArr);
		}else{
			if (brack != -1) {
				//获取到选项数组
				String[] splitStr = bankStr.split(splitRegex_brck);
				//定义新的数组重组选项数组
				String[] bankSortArr = new String[splitStr.length-1];
				String [] bankArr = new String[splitStr.length-1];
				for (int i = 0; i < bankSortArr.length; i++) {
					String repStr = getReplaceStr(splitStr[i+1]);
					bankSortArr[i] = alphabet[i] + ") " + repStr;
					bankArr[i] = repStr;
				}
				//加字母的答案选项
				answerMap.put("option_zm", bankSortArr);
				//未加字母的答案选项
				answerMap.put("option_kb", bankArr);
			}
		}
		return answerMap;
	}
	
	
	/**去掉空格\s、回车\n、换行\r、制表符\t**/
	public static String getReplaceStr(String str){
		String dest = "";
		if(str != null && str != ""){
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
			dest = dest.replaceAll(" ", "");//去掉非英文输入法状态下的空格
			dest = dest.replaceAll("　", "");
			int num = dest.indexOf(BR_LAST_AFTER);
			if(num != -1){
				dest = dest.replaceAll(BR_LAST_AFTER, "");
			}
		}
		return dest;
	}
	
	
	
	/**获取到作文标题内容和图片名称**/
	public static String [] getTitleSplitStr(String titleStr){
		//定义切割数组（图片在标题中可能存在的位置，头部、中间、尾部），按三段来切割即可
		String [] formatArr = null;
		//将字符编码转标签
		String formatStr = getReplaceAllStr(titleStr);
		if (findImage(formatStr)) {
			//实例化返回值
			formatArr = new String [3];
			//获取图片头部位置
			int start = formatStr.indexOf("<img");
			//获取图片尾部位置
			//int end = formatStr.indexOf("."+imgFlag+"\"/>");
			int end = formatStr.indexOf("/>");
			//截取从0的位置到图片头部位置的字符串
			String top = formatStr.substring(0, start);
			//截取从图片头部位置到图片尾部位置的字符串
			//String middle = formatStr.substring(start,end+4+imgFlag.length());
			String middle = formatStr.substring(start,end+2);
			//截取从图片尾部位置到字符串尾部的位置
			//String tail = formatStr.substring(end+4+imgFlag.length(),formatStr.length());
			String tail = formatStr.substring(end+2,formatStr.length());
			//作文标题内容
			String title = top + tail;
			//去掉换行标签
			title = title.replaceAll(P_FIRST, "");
			title = title.replaceAll(P_LAST, "");
			title = title.replaceAll(BR_FIRST, "");
			title = title.replaceAll(BR_LAST, "");
			title = title.replaceAll(BR_LAST_AFTER, "");
			//添加到数组中
			formatArr[0] = title;
			int iix = middle.indexOf("."+imgFlag);
			//获取从0到以图片后缀结尾的字符串
			String imgStr = middle.substring(0, iix+1+imgFlag.length());
			//从字符串后面往前面查找一个斜杠的位置
			int is = imgStr.lastIndexOf("/");
			//获取到图片名称
			String imgName = imgStr.substring(is+1, imgStr.length());
			//将图片名称添加到数组中
			formatArr[1] = imgName;
			//截取图片名称以前的字符串
			String imgSec = imgStr.substring(0,is);
			//查找当前字符串获从后往前的第一个斜杠的位置
			int ie = imgSec.lastIndexOf("/");
			//截取图片上一级目录名称
			String dir = imgSec.substring(ie+1, imgSec.length());
			//目录名称+图片名称
			formatArr[2] = dir+"/"+imgName;
		}else{
			//实例化返回值
			formatArr = new String [1];
			//如果没有图片，那么标题就为当前字符串
			formatArr[0] = formatStr;
		}
		//临时标记，用完清空
		imgFlag = "";
		return formatArr;
	}
	
	
	/**获取到作文标题和图片组合字符串，参数：题库标题，图片路径**/
	public static String getTitleContainImg(String orgTitle,String imgPath){
		String newTitle = "";
		StringBuffer sb = new StringBuffer(orgTitle);
		//"http://192.168.71.26:808/Exam/"
		String proId = "\" id=\"img_path\"";//添加id属性，方便预览动态给src赋值
		String path = imgPath+proId;
		//获取图片src=位置
		int start = sb.indexOf("src=");
		//获取图片后缀名位置
		int end = sb.indexOf("."+imgFlag);
		//newTitle = sb.replace(start+5, end+imgFlag.length()+1,path).toString();
		newTitle = sb.replace(start+5, end+imgFlag.length()+2,path).toString();
		return newTitle;
	}
	
	
	
	
	/**获得标签的开始和结束的位置，截取标签后面的内容**/
	public static String replaceTitle(String str,String regexFirst,String regexLast){
		//转标签
		String formatStr = getReplaceAllStr(str);
		
		
		//获取标签头部位置
		int start = formatStr.indexOf(regexFirst);
		//获取标签尾部位置(从开始标签的位置开始查找结束标签在整个字符串中出现的位置)
		int end = formatStr.indexOf(regexLast,start);
		//截取从0的位置到标签头部位置的字符串
		String top = formatStr.substring(0, start);
		//截取从标签头部位置到标签尾部位置的字符串
		String middle = formatStr.substring(start,end+regexLast.length());
		//截取从标签尾部位置到字符串尾部的位置
		String tail = formatStr.substring(end+regexLast.length(),formatStr.length());
		//作文标题内容
		String title = top + tail;
		
		//去掉字符串中的strong标签
		getRemoveLable(title, "<strong", ">");
		title = TLE;
		title = title.replaceAll(STRONG_FIRST, "");
		title = title.replaceAll(STRONG_LAST, "");
		title = title.replaceAll(BR_FIRST, "");
		title = title.replaceAll(BR_LAST, "");
		return title.trim();
	}
	
	
	/**从字母表获取下标**/
	public static String getSubScriptToAlphabet(String letter){
		String zm = "";
		//去空格并转大写字母
		String str = letter.trim().toUpperCase();
		for (int i = 0; i < alphabet.length; i++) {
			if (str.equals(alphabet[i])) {
				zm = i+"";
			}
		}
		return zm;
	}
	
	/**根据配对题标题查找其选项数量**/
	public static String [] getBankAnswerOption(String title){
		String [] answerOption = null;
		if (title != null && title != "") {
			//记录数组下标临时变量
			int total = 0;
			if (title.indexOf("[A]") != -1) {//如果标题为"[A]"的情况（不能按照A.和A)的情况去判断，因为存在USA.等等以A.结尾的单词）
				if(title.indexOf("[Z]") != -1){//如果选项从[A]~[Z]的话，直接返回
					total = smbt.length;
				}else{
					for (int i = 0; i < smbt.length; i++) {
						if (title.indexOf(smbt[i]) == -1) {
							total = i;
							break;
						}
					}
				}

			}else if(title.indexOf("A.") != -1){
				if(title.indexOf("Z.") != -1){//如果选项从A.~Z.的话，直接返回
					total = sxpt.length;
				}else{
					for (int i = 0; i < sxpt.length; i++) {
						if (title.indexOf(sxpt[i]) == -1) {
							total = i;
							break;
						}
					}
				}

			}else if(title.indexOf("A)") != -1){
				if(title.indexOf("Z)") != -1){//如果选项从A)~Z)的话，直接返回
					total = slbt.length;
				}else{
					for (int i = 0; i < slbt.length; i++) {
						if (title.indexOf(slbt[i]) == -1) {
							total = i;
							break;
						}
					}
				}
			}
			
			//定义字母数组(长度为)
			String [] abc = new String[total];
			for (int i = 0; i < abc.length; i++) {
				//通过下标累加转大写字母赋值给数组
				abc[i] = String.valueOf((char)('A'+i));
				System.out.print(abc[i]+" ");
			}
			answerOption = abc;
		}
		return answerOption;
	}
	
	
	/**拼接答案选项数组组合标签成字符串**/
	public static String getAnswerOptionArrToStr(String [] answerOption){
		String answer = "";
		for (int i = 0; i < answerOption.length; i++) {
			String currentAnswer = "<p>"+answerOption[i]+"</p>";
			answer += currentAnswer;
		}
		return answer;
	}
	

}
