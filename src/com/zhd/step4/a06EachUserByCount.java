package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;
//统计每个用户在整个级联中出现的次数
public class a06EachUserByCount {
	
//获取转发关系
public static void main(String[] args) {
	String srcFileName=args[0];//"G:/zhd20160103/fo1.csv"
	String destFileName=args[1];//"G:/zhd20160103/re.csv"
	BufferedReader br=null;
	FileReader reader=null;
	StringBuilder sBuilder=new StringBuilder();
	//统计用户出现的次数
	Map<String, Integer> userCountMaps=new LinkedHashMap<String, Integer>();
	try {		
		reader = new FileReader(new File(srcFileName));
				br = FileReadUtil.getReadStream(reader);
				//************************************
				String s=null;
				
				while ((s = br.readLine()) != null) {
					String[] ss=s.split("\\}\\|\\|\\|\\}");
					// 原始帖子
					String[] orginal = ss[0].split("\\|\\)\\)\\)\\|");
					String originUid = orginal[1];
					//如果不包含
					if(!userCountMaps.containsKey(originUid)){
						userCountMaps.put(originUid, 1);
					}else {//如果包含
						userCountMaps.put(originUid, userCountMaps.get(originUid)+1);
					}
					// 转发帖子
					String[] follow = ss[1].split("\\}\\]\\]\\]\\}");
				
					for (int i = 0; i < follow.length; i++) {
						String[] subFollow = follow[i].split(",");
						// System.out.println(subFollow.length );
						String followuid = subFollow[1];
						//如果不包含
						if(!userCountMaps.containsKey(followuid)){
							userCountMaps.put(followuid, 1);
						}else {//如果包含
							userCountMaps.put(followuid, userCountMaps.get(followuid)+1);
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
	//对用户的次数进行排序
	// 将Map转化为List集合，List采用ArrayList
	List<Map.Entry<String, Integer>> list_Data = new ArrayList<Map.Entry<String, Integer>>(userCountMaps.entrySet());
	// 通过Collections.sort(List I,Comparator c)方法进行排序
	Collections.sort(list_Data, new Comparator<Map.Entry<String,Integer>>() {

		@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			return o2.getValue().compareTo(o1.getValue());
		}
	});
	for(Map.Entry<String, Integer> entry:list_Data){
		sBuilder.append(entry.getKey()+"||"+entry.getValue()+"\n");
	}
	FileWriteUtil.WriteDocument(destFileName,sBuilder.toString());
}
}
