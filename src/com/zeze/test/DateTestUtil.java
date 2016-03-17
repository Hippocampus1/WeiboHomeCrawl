package com.zeze.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
	* @ClassName: DateTestUtil 
	* @Description:生成月份时间搓  
	* @author zeze
	* @date 2016年3月7日 下午5:10:13 
	*
 */
public class DateTestUtil {

	public static void main(String[] args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String start = "2015-01-01";
		String end = "2015-01-31";
		Date dtS = sdf.parse(start);
		Date dtE = sdf.parse(end);
		for (int i=0;i<14;i++) {
			Calendar rightS = Calendar.getInstance();
			rightS.setTime(dtS);
			rightS.add(Calendar.MONTH, i);// 日期加1个月

			Calendar rightE = Calendar.getInstance();
			rightE.setTime(dtE);
			rightE.add(Calendar.MONTH, i);// 日期加1个月

			Date dt1 = rightS.getTime();
			String reStr = sdf.format(dt1);
			System.out.println(reStr);

			long timestampS = rightS.getTimeInMillis();
			System.out.println(timestampS);

			Date dt2 = rightE.getTime();
			String reStr2 = sdf.format(dt2);
			System.out.println(reStr2);

			long timestampE = rightE.getTimeInMillis();
			System.out.println(timestampE);
			
			System.out.println();
		}

	}

}