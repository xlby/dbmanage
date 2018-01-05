package com.fifedu.util.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fifedu.util.constant.SystemConstants;
import com.sun.org.apache.bcel.internal.generic.NEW;


/**
 * 文件名：DateUtil.java Copyright: 2014-2018 FiF. All Rights Reserved.
 * 
 * @author: Wu jian hua
 * 
 * @since: iTest2.0 产品体系子产品 创建时间：2013-08-09   控制java中的时间
 */

/**
 * 目的：控制java中的时间 途径：利用java.util.Date,java.util.Calendar，来实现对应日期的操作，可以得到java.sql.
 * Date的数据和各种类型的数据 作者：吴建华 时间：2005.10.08 说明 一： Java的Date和Time函数－java.sql.Date
 * 和java.sql.Time只是从数据库读取某几个值，因此有时会造成丢失数据。例如日期和时间2002/05/22 5:00:57 PM 只会显示成
 * 2002/05/22 或5:00:57 PM 。 我们应该了解每种数据库对日期的保存精度。有的数据库，如MySQL
 * 保存精度是毫秒级，而其他一些包括Oracle无法精确到毫秒级。以下的一些操作会因为这种区别而引发问题：
 * 
 * 获取Java 日期。 从数据库中读出日期 试图使用equals( ) 函数来匹配新旧Java日期。如果没有找到毫秒数，equals 方法会返回false
 * 
 * java.sql.Timestamp 比 java.util.Date 类更精确。它包含了一个名为 getTime( )
 * 的方法，不过并不返回精确度，因此你还要使用getNanos( ) ：
 * 
 * long time = timestamp.getTime() + timestamp.getNanos()/1000000;
 * 
 * 由于附加了十亿分之一秒（nanosecond）的精确度，因此java.sql.Timestamp 永远不会和java.util.Date匹配，当然
 * java.util.Date可以匹配 java.sql.Timestamp。 使用java.sql.Time
 * 相关的类很重要也很简单。但它也会造成错误，因为虽然javadoc里很清楚地写明他们之间的区别，可是这两者还是太像了。
 * 
 * 说明二： 1 java.util.date 和 java.sql.date 两者之间的不同，和相互转换方法： java.util.Date
 * 是java.sql.Date（它只包含日期而没有时间部分）,java.sql.Time和java.sql.Timestamp 的父类。
 * 前者是常用的表示时间的类，通常格式化或者得到当前时间都是用它
 * 后者之后在读写数据库的时候用它，因为PreparedStament的setDate()的第2参数和ResultSet的getDate
 * ()方法的第2个参数都是java.sql.Date 因为两者都有getTime方法返回毫秒数，所以可以进行相互转换 java.sql.Date
 * date=new Java.sql.Date(); java.util.Date d=new java.util.Date
 * (date.getTime()); 反过来是一样的
 * 
 * 2 为什么java.sql.Date类型的值插入到数据库中Date字段中会发生数据截取呢？ java.sql.Date是为了配合SQL
 * DATE而设置的数据类型。“规范化”的java.sql.Date只包含年月日信息，时分秒毫秒都会清零。
 * 格式类似：YYYY-MM-DD。当我们调用ResultSet的getDate
 * ()方法来获得返回值时，java程序会参照"规范"的java.sql.Date来格式化数据库中的数值。
 * 因此，如果数据库中存在的非规范化部分的信息将会被劫取。 在sun提供的ResultSet.java中这样对getDate进行注释的： Retrieves
 * the of the designated column in the current row of this
 * <code>ResultSet</code> object as a “java.sql.Date” object in the Java
 * programming language.
 * 
 * 同理。如果我们把一个java.sql.Date值通过PrepareStatement的setDate方法存入数据库时，java程序会对传入的java.
 * sql.Date规范化，非规范化的部分将会被劫取。
 * 然而，我们java.sql.Date一般由java.util.Date转换过来，如：java.sql.Date sqlDate=new
 * java.sql.Date(new java.util.Date().getTime()).
 * 显然，这样转换过来的java.sql.Date往往不是一个规范的java.sql.Date.
 * 
 * 要保存java.util.Date的精确值，需要利用java.sql.Timestamp.
 * 
 * 说明三： Calendar Calendar calendar=Calendar.getInstance(); //获得当前时间，声明时间变量 int
 * year=calendar.get(Calendar.YEAR); //得到年 int
 * month=calendar.get(Calendar.MONTH); //得到月，但是，月份要加上1 month=month+1; int
 * date=calendar.get(Calendar.DATE); //获得日期 String
 * today=""+year+"-"+month+"-"+date+"";
 */
public class DateUtil {
	public final static int WeekSpan = 7;// 每周7天
	public static int month[] = {// 每个月的天数
	31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/* 判断是否闰年 */
	private static boolean LeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			// System.out.println(year+" is a leap year.");
			return true;
		} else {
			// System.out.println(year+" is not a leap year.");
			return false;
		}
	}

	/* java.sql.Date到java.util.Date转换 */
	public static java.util.Date changeToUtilDate(java.sql.Date sqldate) {
		return new java.util.Date(sqldate.getTime());

	}

	/*
	 * java.util.Date到java.sql.Date转换，会发生数据截取java.sql.Date 使用的是短日期格式, 不能够精确到时分秒.
	 * 所以, 如果要把时分秒显示出来, 需要使用 java.util.date
	 */
	public static java.sql.Date changeToSqlDate(java.util.Date utildate) {
		return new java.sql.Date(utildate.getTime());

	}

	/* java.util.Date到java.sql.Timestamp转换,不会丢失java.util.Date的精确值 */
	public static java.sql.Timestamp changeToSqlTimestamp(java.util.Date utildate) {
		return new java.sql.Timestamp(utildate.getTime());
	}

	/* 对个位数的月份之前补零 */
	private static String impleMonth(int month) {
		String monthStr = new Integer(month).toString();
		if (monthStr.length() == 1) {
			monthStr = "0" + monthStr;
		}
		return monthStr;
	}

	/* 对个位数的日子之前补零 */
	private static String impleDay(int day) {
		String dayStr = new Integer(day).toString();
		if (dayStr.length() == 1) {
			dayStr = "0" + dayStr;
		}
		return dayStr;
	}

	/* 得到当前周的第一天（周一）的日期 */
	public static String getWeekFirstDate() {
		Date da = new Date();
		int dayOfWeek = da.getDay(); // 当前日期是星期几
		long fromWeekFirstInMillis = (dayOfWeek - 1) * 24 * 60 * 60 * 1000; // 与该周第一天相隔的毫秒数
		da.setTime(da.getTime() - fromWeekFirstInMillis);
		String weekFirstDay = new Integer(da.getYear() + 1900).toString();
		weekFirstDay = weekFirstDay + "-" + impleMonth(da.getMonth() + 1);
		weekFirstDay = weekFirstDay + "-" + impleDay(da.getDate());
		return weekFirstDay;
	}

	/* 得到当前周的最后一天（周日）的日期 */
	public static String getWeekLastDate() {
		Date da = new Date();
		int dayOfWeek = da.getDay(); // 当前日期是星期几
		long toWeekLastInMillis = (WeekSpan - dayOfWeek) * 24 * 60 * 60 * 1000; // 与该周最后一天相隔的毫秒数
		da.setTime(da.getTime() + toWeekLastInMillis);
		String weekLastDay = new Integer(da.getYear() + 1900).toString();
		weekLastDay = weekLastDay + "-" + impleMonth(da.getMonth() + 1);
		weekLastDay = weekLastDay + "-" + impleDay(da.getDate());
		return weekLastDay;
	}

	/* 得到当前月的第一天的日期 */
	public static String getMonthFirstDate() {
		Date da = new Date();
		long dayOfMonth = da.getDate(); // 当前日期是本月第几天
		long fromMonthFirstInMillis = (dayOfMonth - 1) * 24 * 60 * 60 * 1000; // 与该月第一天相隔的毫秒数
		da.setTime(da.getTime() - fromMonthFirstInMillis);
		String MonthFirstDay = new Integer(da.getYear() + 1900).toString();
		MonthFirstDay = MonthFirstDay + "-" + impleMonth(da.getMonth() + 1);
		MonthFirstDay = MonthFirstDay + "-" + impleDay(da.getDate());
		return MonthFirstDay;
	}

	/* 得到当前月的最后一天的日期 */
	public static String getMonthLastDate() {
		Date da = new Date();
		long dayOfMonth = da.getDate(); // 当前日期是本月第几天
		int monthSpan = 0;
		if ((da.getMonth() + 1) == 2) {
			if (LeapYear(da.getYear() + 1900)) {
				monthSpan = 29;
			} else {
				monthSpan = month[da.getMonth()];
			}
		} else {
			monthSpan = month[da.getMonth()];

		}
		// System.out.println("the montheSpan: "+monthSpan);

		long toMonthLastInMillis = (monthSpan - dayOfMonth) * 24 * 60 * 60 * 1000; // 与该月最后一天相隔的毫秒数
		da.setTime(da.getTime() + toMonthLastInMillis);
		String MonthLastDay = new Integer(da.getYear() + 1900).toString();
		MonthLastDay = MonthLastDay + "-" + impleMonth(da.getMonth() + 1);
		MonthLastDay = MonthLastDay + "-" + impleDay(da.getDate());
		return MonthLastDay;
	}

	/* 得到当前的日期 */
	public static String getCurrentDate() {
		Date da = new Date();
		String currentDay = new Integer(da.getYear() + 1900).toString();
		currentDay = currentDay + "-" + impleMonth(da.getMonth() + 1);
		currentDay = currentDay + "-" + impleDay(da.getDate());
		return currentDay;
	}

	/**
	 * add by gm
	 */

	// 按长度把字符串前补0
	public static String strLen1(String s, int len) {
		if (isNullStr(s)) {
			s = "";

		}
		int strLen = s.length();
		for (int i = 0; i < len - strLen; i++) {
			s = "0" + s;
		}
		return s;
	}

	public static String strLen(String s, int len) {
		if (isNullStr(s)) {
			s = "";
		}
		if (s.length() == len) {
			return s;
		}
		for (int i = 0; i < len - s.length(); i++) {
			s = "0" + s;
			if (s.length() == len) {
				break;
			}
		}
		return s;
	}

	// 判断字符串是否为空
	public static boolean isNullStr(String s) {
		if (s == null || s.trim().length() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	// 判断字符串数组是否为空
	public static boolean isNullStr(String[] s) {
		if ((s == null) || (s.length <= 0)) {
			return true;
		} else {
			return false;
		}
	}

	// 返回日历的年字符串
	public static String getYear(Calendar cal) {
		return String.valueOf(cal.get(cal.YEAR));
	}

	// 返回日历的月字符串(两位)
	public static String getMonth(Calendar cal) {
		return strLen(String.valueOf(cal.get(cal.MONTH) + 1), 2);
	}

	// 返回日历的日字符串(两位)
	public static String getDay(Calendar cal) {
		return strLen(String.valueOf(cal.get(cal.DAY_OF_MONTH)), 2);
	}

	// 返回日历的时字符串(两位)
	public static String getHour(Calendar cal) {
		return strLen(String.valueOf(cal.get(cal.HOUR_OF_DAY)), 2);
	}

	// 返回日历的分字符串(两位)
	public static String getMinute(Calendar cal) {
		return strLen(String.valueOf(cal.get(cal.MINUTE)), 2);
	}

	// 返回日历的秒字符串(两位)
	public static String getSecond(Calendar cal) {
		return strLen(String.valueOf(cal.get(cal.SECOND)), 2);
	}

	// 由"yyyy-mm-dd"型串转换为Integer型yyyymmdd
	public static Integer getNumDate(String strDate) {
		String theDay = null;
		if (strDate != null && strDate.length() > 9) {
			theDay = strDate.substring(0, 4) + strDate.substring(5, 7) + strDate.substring(8, 10);
			return new Integer(theDay);
		}
		return null;
	}

	// 返回日历的日期字符串（格式："yyyy-mm-dd"）
	public static String getDateStr(Calendar cal) {
		return getYear(cal) + "-" + getMonth(cal) + "-" + getDay(cal);
	}

	// 返回日历的日期字符串（格式："yyyymmdd"）
	public static String getDateStr2(Calendar cal) {
		return getYear(cal) + getMonth(cal) + getDay(cal);
	}

	// 返回日历的日期字符串（格式："yyyy-mm-dd"）
	public static String getDateStr3(Integer date) {
		String result = "";
		String dateStr = String.valueOf(date);
		if (dateStr != null && dateStr.length() == 8) {
			result = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6) + "-" + dateStr.substring(6, 8);
		}
		return result;
	}

	// 返回日历的时间字符串（格式："hh:ss"）
	public static String getTimeStr(Calendar cal) {
		return getHour(cal) + ":" + getMinute(cal);
	}

	// 返回日历的时间字符串（格式："hh:ss:mm"）
	// add by LiuQinghua
	public static String getTime2Str(Calendar cal) {
		return getHour(cal) + ":" + getMinute(cal) + ":" + getSecond(cal);
	}

	// 返回日历的日期时间字符串（格式："yyyy-mm-dd hh:ss"）
	public static String getDate(Calendar cal) {
		return getDateStr(cal) + " " + getTimeStr(cal);
	}

	// 返回日期字符串("yyyy-mm-dd hh:ss:mm")的年
	public static int getYear(String s) {
		if (s == null || s.length() < 10) {
			return 1970;
		}
		return Integer.parseInt(s.substring(0, 4));
	}

	// 返回日期字符串("yyyy-mm-dd hh:ss:mm")的月
	public static int getMonth(String s) {
		if (s == null || s.length() < 10) {
			return 1;
		}
		return Integer.parseInt(s.substring(5, 7));
	}

	// 返回日期字符串("yyyy-mm-dd hh:ss:mm")的日
	public static int getDay(String s) {
		if (s == null || s.length() < 10) {
			return 1;
		}
		return Integer.parseInt(s.substring(8, 10));
	}

	// 返回日期字符串("yyyy-mm-dd hh:ss:mm")的时
	public static int getHour(String s) {
		if (s == null || s.length() < 16) {
			return 0;
		}
		return Integer.parseInt(s.substring(11, 13));
	}

	// 返回日期字符串("yyyy-mm-dd hh:ss:mm")的分
	public static int getMinute(String s) {
		if (s == null || s.length() < 16) {
			return 0;
		}
		return Integer.parseInt(s.substring(14, 16));
	}

	// 返回日期字符串("yyyy-mm-dd hh:ss:mm")的秒
	public static int getSecond(String s) {
		if (s == null || s.length() < 18) {
			return 0;
		}
		return Integer.parseInt(s.substring(16, 18));
	}

	// 返回日期时间字符串对应的日历（格式："yyyy-mm-dd hh:ss"）
	public static Calendar getCal(String s) {
		Calendar cal = Calendar.getInstance();
		cal.set(getYear(s), getMonth(s), getDay(s), getHour(s), getMinute(s), getSecond(s));
		return cal;
	}

	// 返回日期时间字符串对应的SQL日期（格式："yyyy-mm-dd hh:ss"）
	public static java.sql.Date getSqlDate(String s) {
		return java.sql.Date.valueOf(s);
	}

	// 返回当天日期对应的SQL日期（）
	public static java.sql.Date getSqlDate() {
		return java.sql.Date.valueOf(getNowDate());
	}

	// 取当前日期时间的字符串，格式为"yyyy-mm-dd hh:ss"
	public static String getNow() {
		Calendar now = Calendar.getInstance();
		return getDateStr(now) + " " + getTimeStr(now);
	}

	// 取当前日期时间的字符串，格式为"yyyy-mm-dd hh:ss:mm"
	// add by LiuQinghua
	public static String getNow2() {
		Calendar now = Calendar.getInstance();
		return getDateStr(now) + " " + getTime2Str(now);
	}

	// 取当前日期的整形串，格式为"yyyymmdd"
	public static Integer getNumDate() {
		Calendar now = Calendar.getInstance();
		return new Integer(getDateStr2(now));
	}

	// 取给定日期的整形串，格式为"yyyymmdd"
	public static Integer getNumDate(Calendar cal) {
		return new Integer(getDateStr2(cal));
	}

	// 取当前日期的字符串，格式为"yyyy-mm-dd"
	public static String getNowDate() {
		Calendar now = Calendar.getInstance();
		return getDateStr(now);
	}

	// 取当前时间的字符串，格式为"hh:ss"
	public static String getNowTime() {
		Calendar now = Calendar.getInstance();
		return getTimeStr(now);
	}

	// 取当前时间的字符串
	public static String getCurrentTimeMillisStr() {

		return (new Long(System.currentTimeMillis()).toString());
	}

	// 取当前时间的字符串
	public static String changTimeMillisToStr(String time) {
		long t = 0l;
		try {
			t = Long.parseLong(time);
		} catch (Exception e) {
			return "";
		}
		Date date = new Date();
		date.setTime(t);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return getDateStr(cal) + " " + getTimeStr(cal);
	}

	public static String changTimeMillisToStr(long time) {
		String str = "";
		try {
			str = Long.toString(time);
		} catch (Exception e) {
		}
		return changTimeMillisToStr(str);
	}

	/**
	 * 格式化字符串为日期的函数.
	 * 
	 * @param str
	 *            字符串.
	 * @param format
	 *            转换格式如:"yyyy-MM-dd mm:ss"
	 * @return 字符串包含的日期. 注意：SimpleDateFormat是非线程安全的
	 */
	public static Date parseDate(String strDate, String format) {
		try {
			if (strDate == null || strDate.equals(""))
				return null;
			DateFormat df = new SimpleDateFormat(format);
			return df.parse(strDate);
		} catch (Exception e) {
		}
		return new Date();
	}

	/**
	 * 格式化日期为字符串函数.
	 * 
	 * @param date
	 *            日期.
	 * @param format
	 *            转换格式."yyyy-MM-dd mm:ss"
	 * @return 日期转化来的字符串. 注意：SimpleDateFormat是非线程安全的（为了渐少new
	 *         的次数而把SimpleDateFormat做成成员或者静态成员，但这样的做法是隐含着错误的，是不 安全的）
	 */
	public static String formatDate(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat simpleDateFORMat = new SimpleDateFormat(format);
		return simpleDateFORMat.format(date);
	}

	/**
	 * 取得preNum天前的当前时间字符串函数.
	 * 
	 * @param preNum
	 *            天数
	 * @return preNum天前的当前时间字符串，格式"yyyy-MM-dd hh:mm:ss".
	 */
	public static String getPreDateStr(int preNum) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -preNum);
		result = getDateStr(cal) + " " + getTime2Str(cal);
		return result;
	}

	/*
	 * 当前时间与指定日期比较,日期字符串("yyyy-mm-dd hh:ss:mm")
	 */
	public static boolean compareDate(String speciDate) {
		Date date = new Date();

		boolean isbefore = false;
		DateFormat df = DateFormat.getDateTimeInstance();

		// String now=df.format(date); //当前时间
		try {
			Date temp = df.parse(speciDate);

			isbefore = date.before(temp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isbefore;

	}

	public static void main(String args[]) {
	   // Date date = new Date(1357028825000l);
		//System.out.println(formatDate(date,"yyyy-MM-dd"));
		//System.out.println(getCurrentDate());
		//System.out.println(compareDate("2010-03-20", "2010-03-31"));
		String cl[]={"22","44"};
		System.out.println(cl.toString());
		System.out.println(String.valueOf(cl));
		
		String str = formatDate(new Date(), "yyyy:MM:dd hh:mm");
		System.out.println(str);
	}

	// 也可以考虑将时间换算成long,然后再比较大小
	public static int compareDate(String time1, String time2) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(time1));
			c2.setTime(df.parse(time2));
		} catch (java.text.ParseException e) {
			System.err.println("格式不正确");

		}
		int result = c1.compareTo(c2);
		if (result == 0) {
			// System.out.println("c1相等c2");
		} else if (result < 0) {
			// System.out.println("c1小于c2");
			result = -1;
		} else {
			// System.out.println("c1大于c2");
			result = 1;

		}
		return result;
	}

	/**
	 * 获取两个时间段的差(获取的是分钟数)
	 * 
	 * @return
	 * @throws ParseException
	 * 
	 * */
	public static int getSubTime(Date planStart, Date planEnd) throws ParseException {
		long startTime = planEnd.getTime() - planStart.getTime();
		int result = (int) ((startTime / 1000) / 60);
		return result;
	}
	
	/**
	 * 获取两个时间段的差(获取的是分钟数)
	 * 
	 * @return
	 * @throws ParseException
	 * 
	 * */
	public static int getSubTimeAsSecond(Date planStart, Date planEnd) throws ParseException {
		long startTime = planEnd.getTime() - planStart.getTime();
		int result = (int) ((startTime / 1000));
		return result;
	}

	/****************** 格式化日期 ***********************/

	/**
	 * 转换 HH:mm:ss格式到毫秒
	 * 
	 * @return
	 */
	public static int transitionStrToMs(String timeStr) {
		String[] HHmmss = timeStr.split(SystemConstants.SEPARATOR_COLON);
		int timeMs = 0;
		for (int i = 0; i < HHmmss.length; i++) {
			int time = Integer.valueOf(HHmmss[i]);
			if (i == 0) {
				timeMs += time * 60 * 60 * 1000;
			} else if (i == 1) {
				timeMs += time * 60 * 1000;
			} else if (i == 2) {
				timeMs += time * 1000;
			}
		}
		return timeMs;
	}

	/**
	 * 格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String formatType_full_1 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 格式 yyyy年MM月dd日 HH时mm分ss秒
	 */
	public static final String formatType_full_ch = "yyyy年MM月dd日 HH时mm分ss秒";

	public static Date getDateFromStr(String date, String formatType) {
		SimpleDateFormat simpleDateFORMat = new SimpleDateFormat(formatType);
		try {
			return simpleDateFORMat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 计算时间差是Oracle
	 * DATA数据类型的一个常见问题。Oracle支持日期计算，你可以创建诸如“日期1－日期2”这样的表达式来计算这两个日期之间的时间差。
	 * 
	 * 
	 * 
	 * 一旦你发现了时间差异，你可以使用简单的技巧来以天、小时、分钟或者秒为单位来计算时间差。为了得到数据差，你必须选择合适的时间度量单位，
	 * 这样就可以进行数据格式隐藏。
	 * 
	 * 使用完善复杂的转换函数来转换日期是一个诱惑，但是你会发现这不是最好的解决方法。
	 * 
	 * round(to_number(end-date-start_date))- 消逝的时间（以天为单位）
	 * 
	 * round(to_number(end-date-start_date)*24)- 消逝的时间（以小时为单位）
	 * 
	 * round(to_number(end-date-start_date)*1440)- 消逝的时间（以分钟为单位）
	 * 
	 * 显示时间差的默认模式是什么？为了找到这个问题的答案，让我们进行一个简单的SQL *Plus查询。
	 * 
	 * SQL> select sysdate-(sysdate-3) from dual;
	 * 
	 * SYSDATE-(SYSDATE-3) ------------------- 3
	 * 
	 * 这里，我们看到了Oracle使用天来作为消逝时间的单位，所以我们可以很容易的使用转换函数来把它转换成小时或者分钟。然而，当分钟数不是一个整数时，
	 * 我们就会遇到放置小数点的问题。
	 * 
	 * Select (sysdate-(sysdate-3.111))*1440 from dual;
	 * 
	 * 
	 * (SYSDATE-(SYSDATE-3.111))*1440 ------------------------------ 4479.83333
	 */
}
