package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;

public class CaseCade {
public static void main(String[] args) {
	BufferedReader br = null;
	FileReader reader = null;
	BufferedReader br2 = null;
	FileReader reader2 = null;
	String dir = "/mnt/disk/daeteam/zhd/data_processed_lcj/mess20160102/all/";
	//String dir = "G:\\zhd20160103\\";
	Map<String,String> originMaps=new LinkedHashMap<String,String>();
	Map<String, String> followMaps=new LinkedHashMap<String, String>();
	try { 
		reader = new FileReader(new File(dir + "uniqueemoticonfilterOriginal1.csv"));
		br = FileReadUtil.getReadStream(reader);
		reader2 = new FileReader(new File(dir + "uniqueemoticonfilterZhuanfa1.csv"));
		br2 = FileReadUtil.getReadStream(reader2);
		//原始的帖子
		String str = null;
		while ((str = br.readLine()) != null) {
			String[] ss = str.split("\\}\\|\\|\\|\\}");
		//	System.out.println(str);
			//mid+{uid+content+timestamp}
			originMaps.put(ss[0], ss[1]+"||"+ss[2]+"||"+ss[3]);
		}
		//转发的帖子
		String str2 = null;
		while ((str2 = br2.readLine()) != null) {
			//System.out.println(str2);
			//mid+{uid+followid}
			String[] ss = str2.split("\\}\\|\\|\\|\\}");
			followMaps.put(ss[0], ss[1]+"||"+ss[2]+"||"+ss[3]+"||"+ss[4]);
		}
	} catch (Exception e) {
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
			if (br2 != null)
				try {
					br2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (reader2 != null)
				try {
					reader2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	String destDir="/mnt/disk/daeteam/zhd/data_processed_lcj/mess20160102/all/casecade/";
	//String destDir="G:/zhd20160103/casecade/";

	//遍历原始文件
	for(Map.Entry<String,String> it:originMaps.entrySet() ){
		//System.out.println(it.getKey());	
		StringBuilder sBuilder=new StringBuilder();
		Set<String> tempSet=new LinkedHashSet<String>();
		String originKeyId=it.getKey();//原始发布文件的id
		tempSet.add(originKeyId);
//		System.err.println(originKeyId);
		sBuilder.append(originKeyId+"||"+it.getValue()+"\n");
		int count=0;
		for(Map.Entry<String,String> iy:followMaps.entrySet() ){
			//System.out.println(iy.getKey());
			String tempId=iy.getValue().split("\\|\\|")[0];//转发用户id
			String followId=iy.getValue().split("\\|\\|")[1];//转发的id
				// System.out.println(iy.getKey());
			if(tempSet.contains(followId)){
				//System.out.println("kkkkk");
				tempSet.add(iy.getKey());//间接的转发
				count++;
				//sBuilder.append("||"+followId);
			}
		}
		//不断循环，把直接和间接的级联都取出来哈
		int tempcount=0;
		while (true) {
			for(Map.Entry<String,String> iy:followMaps.entrySet() ){
			//System.out.println(iy.getKey());
			String followId=iy.getValue().split("\\|\\|")[1];//转发的id
//			String content=iy.getValue().split("\\|\\|")[2];
//			String timeStamp=iy.getValue().split("\\|\\|")[3];
				// System.out.println(iy.getKey());
			if(tempSet.contains(followId)){
				//System.out.println("kkkkk");
				tempSet.add(iy.getKey());//间接的转发
			//	sBuilder.append("||"+iy.getKey());
				sBuilder.append(iy.getKey()+"||"+iy.getValue()+"\n");
				tempcount++;
				//followMaps.remove(iy.getKey());//移除掉
			}	
		}
			if(count==tempcount) break;
			count=tempcount;
			tempcount=0;
		}
		System.out.println(tempcount);
		if(tempcount>=1){
		//进行写入
		FileWriteUtil.WriteDocument(destDir+it.getKey()+".csv",sBuilder.toString() );
		}
//		sBuilder.append("");
//		if(sBuilder.toString().contains("||")){
//		System.out.println(sBuilder.toString());}
	}
}
}
