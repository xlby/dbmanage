package com.fifedu.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	public static void main(String[] args) {
		
	}
	
	public static String dateToString(Date date,String format){
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(date);
		
	}
}
