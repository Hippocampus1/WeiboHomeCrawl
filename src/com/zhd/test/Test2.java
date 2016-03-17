package com.zhd.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test2 {
public static void main(String[] args) throws ParseException {
//	StringBuilder sb=new StringBuilder();
//	sb.append("11,");
//	sb.append("22,");
//	sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",")+1, "");
//	System.out.println(sb.lastIndexOf(","));
//	sb.append("\n");
//	System.out.println(sb.toString());
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date date = df.parse("2015-04-01");
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	long timestamp = cal.getTimeInMillis();
	System.out.println(timestamp);
	Date date2 = df.parse("2015-04-30");
	Calendar cal2 = Calendar.getInstance();
	cal2.setTime(date2);
	long timestamp2 = cal2.getTimeInMillis();//获得时间搓
	System.out.println(timestamp2);
	System.err.println(date);
	//最大的周期一个月  2505600
	//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	//Long timeTran = new Long("1422547200000");
	//String date3= format.format(timeTran);
	//System.out.println(date3+"zeze");
}
}
