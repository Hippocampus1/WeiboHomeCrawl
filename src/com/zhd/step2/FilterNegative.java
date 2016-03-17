package com.zhd.step2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import com.zhd.util.FileReadUtil;

public class FilterNegative {
public static void main(String[] args) {
	BufferedReader br=null;
	FileReader reader=null;
	String dir="/mnt/disk/daeteam/zhd/data_processed_lcj/mess1201/";
	File file = null;
	file = new File(dir + "negative.txt");
	Set<String> set=new LinkedHashSet<String>();
	//StringBuffer sbBuffer=new StringBuffer();
	 try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while((str=br.readLine())!=null){
				set.add(str);	
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
	 System.out.println("总共"+set.size()+"个表情符");
	 for(String ss:set){
		 System.out.println(ss);
	 }
	 //遍历文件
//	 try {
//		ReadFileList.readfile("/mnt/disk/daeteam/zhd/data_processed_lcj/submess/mess/allmess");
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	 String messDir="/mnt/disk/daeteam/wuqi/messages/";
		String[] fileNameIn=new String[]{"mess_1.csv","mess_2.csv","mess_3.csv","mess_4.csv","mess_5.csv","mess_6.csv"};
	 String dir2="/mnt/disk/daeteam/zhd/data_processed_lcj/mess1201/negative/";
	   FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
	// List<String> alList= ReadFileList.filename();
		for(int i=0;i<6;i++){
		 System.out.println(messDir+fileNameIn[i]+"开始运行了");
		 try {
				reader = new FileReader(new File(messDir+fileNameIn[i]));
				br = FileReadUtil.getReadStream(reader);
				fw = new FileWriter(new File(dir2+"negative_emoticon"+i+".csv"), true);
				bw = new BufferedWriter(fw);
				pw = new PrintWriter(bw);
				String str = null;
				//strSubMess=new StringBuilder();
				while((str=br.readLine())!=null){
					String[] ss=str.split("!@#");
				//	System.out.println(str);
					if(ss.length!=3)continue;
					String content=ss[1];
					for(String emoction:set){
						if(content.contains(emoction)){
							pw.write(str+"\n");
							break;
						}
					}
				}
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fw != null) {
					try {
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
			}
	 }
}
}
