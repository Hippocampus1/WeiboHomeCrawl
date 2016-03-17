package com.zeze.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;

public class Maximum_subgraph_user_name_id {
public void user_name_id_process(){
	BufferedReader br=null;
	FileReader reader=null;
	String dir = "/home/fzuir/ZhdExp/data_processed_lcj/";
	//String dir="J:/";
	File file = null;
	file = new File(dir + "subgraph.txt");// 从subgraph.txt读出最大子图的id号
	int max_subgraph_id=0;
    try {
		reader = new FileReader(file);
		br = FileReadUtil.getReadStream(reader);
		String str = null;
		str=br.readLine();
		max_subgraph_id=Integer.parseInt(str.split("\\|\\|")[0]);
		//System.out.println(max_subgraph_id);
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
    file = new File(dir + "belong.txt");//belong.txt保存的是每一个点所属的子图id
	HashMap<Integer, String> belongMaps=new LinkedHashMap<Integer,String>();
	int n=0;//计数器
	int id=0;//存储id号码
    try {
		reader = new FileReader(file);
		br = FileReadUtil.getReadStream(reader);
		String str = null;
		while((str=br.readLine())!=null){
			++n;
			id=Integer.parseInt(str);
			if(max_subgraph_id==id){//属于最大子图
				belongMaps.put(n, "1");
				//System.out.println(n);
			}else{
				belongMaps.put(n, "0");
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
    file = new File(dir + "user_name_id.txt");
    StringBuilder sbUserNameId=new StringBuilder();
       try {
   		reader = new FileReader(file);
   		br = FileReadUtil.getReadStream(reader);
   		String str = null;
   		while((str=br.readLine())!=null){
   			String[] ss=str.split("\\|\\|");
   			String name=ss[0];
   		    int idTemp=Integer.parseInt(ss[1]);
   			//System.out.println(belongMaps.get(idTemp));
   			if("1".equals(belongMaps.get(idTemp))){//属于最大子图
   				//System.out.println("kkk");
   				System.out.println(name+"||"+idTemp);
   				sbUserNameId.append(name+"||"+idTemp+"\n");
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
     //写入
		String file2=dir+"maximum_subgraph_user_name_id.txt";
		FileWriteUtil.WriteDocument(file2, sbUserNameId.toString()); 
}
public static void main(String[] args) {
	long nd = 1000 * 24 * 60 * 60;
	long nh = 1000 * 60 * 60;
	long nm = 1000 * 60;
	 long ns = 1000;
	// 获得两个时间的毫秒时间差异
	System.out.println("开始运行");
	Date nowDate = new Date();
	new Maximum_subgraph_user_name_id().user_name_id_process();
	// System.out.println("已完成");
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
}
