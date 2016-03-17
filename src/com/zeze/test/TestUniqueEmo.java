package com.zeze.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.zhd.util.FileReadUtil;
/**
 * 
	* @ClassName: TestUniqueEmo 
	* @Description: 查找重复的表情符号  
	* @author zeze
	* @date 2016年3月2日 下午3:23:31 
	*
 */
public class TestUniqueEmo {
public static void main(String[] args) {
	BufferedReader br=null;
	FileReader reader=null;
	//String dir = "/home/fzuir/ZhdExp/data_processed_lcj/";
	String dir="C:\\Users\\zeze\\Desktop\\毕业设计\\数据集\\";
	//String dir="J:/";
	File file = null;
	file = new File(dir + "positive.txt");
	//file = new File(dir + "negative.txt");
	Set<String> set=new LinkedHashSet<String>();
	StringBuffer sbBuffer=new StringBuffer();
	 try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while((str=br.readLine())!=null){
				if(set.contains(str)){
					System.out.println(str+"-----------------------");
				}
				set.add(str);
//				if(str.contains("[")){
//				sbBuffer.append(str.replace("[", "\\[").replace("]", "\\]")+"|");}
//				//sbBuffer.append(str+"|");
			//	System.out.println(str);
			sbBuffer.append(str+"||"+"1"+"\n");
			
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
	// System.out.println(sbBuffer.toString());
	// System.err.println(set.size());
//	 for(String ss:set){
//		 System.out.println(ss);
//	 }
	 file = new File(dir + "negative.txt");
	 try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while((str=br.readLine())!=null){
			//	System.out.println(str);
//				if(str.contains("[")){
//					sbBuffer.append(str.replace("[", "\\[").replace("]", "\\]")+"|");}
//					//sbBuffer.append(str+"|");
			sbBuffer.append(str+"||-1"+"\n");
				if(set.contains(str)){
					System.out.println(str+"-----------------------");
				}
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
	 System.out.println(sbBuffer.toString());
	 System.err.println(set.size());
	 //FileWriteUtil.WriteDocument("G:/emotion1201",sbBuffer.toString() );
}
}
