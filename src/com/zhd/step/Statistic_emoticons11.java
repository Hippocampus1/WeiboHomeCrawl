package com.zhd.step;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;
/**
 * 
	* @ClassName: Statistic_emoticons11 
	* @Description:  输入文件： sort_filter_positive.csv,sort_filter_negative.csv
	*                输出文件：statistic_positive_emoticon.txt,statistic_negative_emoticon.txt
	* @author zeze
	* @date 2016年3月3日 上午9:44:10 
	*
 */
public class Statistic_emoticons11 {
	public static void main(String[] args) {
		//String dir = "/home/fzuir/ZhdExp/data_processed_lcj/";
		//String dir="G:/";
		String dir="/mnt/disk/daeteam/zhd/data_processed_lcj";
		String in=dir + "/submess/positive/sort_filter_positive.csv";
		String out=dir+"/submess/positive/statistic_positive_emoticon.txt";
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		// 获得两个时间的毫秒时间差异
		System.out.println("开始运行");
		Date nowDate = new Date();
		new  Statistic_emoticons11().statisticEmotions(in, out);
		in=dir + "/submess/negative/sort_filter_negative.csv";
		out=dir+"/submess/negative/statistic_negative_emoticon.txt";
		new  Statistic_emoticons11().statisticEmotions(in, out);
		Date endDate = new Date();
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		 long sec = diff % nd % nh % nm / ns;
		System.out.println(day + "天" + hour + "小时" + min + "分钟"+sec+"秒");
	}
public void statisticEmotions(String in,String out){
	BufferedReader br=null;
	FileReader reader=null;
	//String dir = "/home/fzuir/ZhdExp/data_processed_lcj/";
	//String dir="J:/";
	File file = null;
	file = new File(in);
	Map<String, Integer> maps=new LinkedHashMap<String,Integer>();
    try {
		reader = new FileReader(file);
		br = FileReadUtil.getReadStream(reader);
		String str = null;
		str=br.readLine();
		while((str=br.readLine())!=null){
   			String[] ss=str.split("!@#");
   			String uid=ss[0];
   			if(!maps.containsKey(uid)){
   			maps.put(uid, 1);	
   			}else{
   				maps.put(uid, maps.get(uid)+1);
   			}
   			}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (br != null)
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (reader != null)
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
    StringBuilder sBuilder=new StringBuilder();
    //遍历map
    for (Map.Entry<String,Integer> entry : maps.entrySet()) {
		// System.out.println("key= " + entry.getKey() + " and value= "
		// + entry.getValue());
		sBuilder.append(entry.getKey() + "||" + entry.getValue() + "\n");
	}
   // String fileName="/submess/positive/statistic_positive_emoticon.txt";
   FileWriteUtil.WriteDocument(out, sBuilder.toString());
}
}
