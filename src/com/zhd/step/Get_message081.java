package com.zhd.step;

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

public class Get_message081 {
public void  message_process(){
	BufferedReader br=null;
	FileReader reader=null;
	//String dir = "/home/fzuir/ZhdExp/data_processed_lcj/";
	String dir="/mnt/disk/daeteam/zhd/data_processed_lcj/";
	//String dir="J:/";
	File file = null;
	file = new File(dir + "maximum_subgraph_user_name_id.txt");
	HashMap<String, Integer> mapIdName=new LinkedHashMap<String,Integer>();
	 try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while((str=br.readLine())!=null){
				String[] ss=str.split("\\|\\|");
				String name=ss[0];
				int id=Integer.parseInt(ss[1]);
				mapIdName.put(name, id);
			
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
	String messDir="/mnt/disk/daeteam/wuqi/messages/";
	String[] fileNameIn=new String[]{"mess_1.csv","mess_2.csv","mess_3.csv","mess_4.csv","mess_5.csv","mess_6.csv"};
	String[] fileNameOut=new String[]{"subgraph_mess1.csv","subgraph_mess2.csv","subgraph_mess3.csv","subgraph_mess4.csv",
			"subgraph_mess5.csv",	"subgraph_mess6.csv"};
	StringBuilder strSubMess=null;
	for(int i=0;i<6;i++){
		String fileName=messDir+fileNameIn[i];
		file=new File(fileName);
		int noInSubCon=0;
		 try {
				reader = new FileReader(file);
				br = FileReadUtil.getReadStream(reader);
				String str = null;
				strSubMess=new StringBuilder();
				while((str=br.readLine())!=null){
					String[] ss=str.split("!@#");
					//格式是===》世界500强商务英语!@#简单形容词的高级表达，值得收藏~!@#1396513993000
					if(mapIdName.containsKey(ss[0])){
						//是否为最大子图中的用户名
						int id=mapIdName.get(ss[0]);
						strSubMess.append(id+"!@#"+str+"\n");
						//System.out.println(id+"!@#"+str);
					}else{
						noInSubCon++;
					}
				
				}
				System.out.println(fileName+"==>不在最大子图中的条数："+noInSubCon);
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
		 String destFile=dir+"submess/"+fileNameOut[i];
		 FileWriteUtil.WriteDocument(destFile, strSubMess.toString());
		 strSubMess.setLength(0);
	}
}
public static void main(String[] args) {
	long nd = 1000 * 24 * 60 * 60;
	long nh = 1000 * 60 * 60;
	long nm = 1000 * 60;
	 long ns = 1000;
	// 获得两个时间的毫秒时间差异
	System.out.println("开始运行");
	Date nowDate = new Date();
	new Get_message081().message_process();
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
