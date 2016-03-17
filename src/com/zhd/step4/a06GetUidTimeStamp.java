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

/**
 * 获取用户ID和时间搓
 * 
 * @author houdong
 *
 */
public class a06GetUidTimeStamp {
	public static void main(String[] args) {
		// String originFileName=args[0];
		// String srcFileName=args[1];
		//
		// String destFileName=args[2];
		BufferedReader br = null;
		FileReader reader = null;
		Map<String, Long> eachCaseCades = null;
		StringBuilder sBuilder=new StringBuilder();
		try {
			reader = new FileReader(new File("G:/zhd20160103/fo1.csv"));
			br = FileReadUtil.getReadStream(reader);
			// ************************************
			String s = null;
			while ((s = br.readLine()) != null) {
				eachCaseCades = new LinkedHashMap<String, Long>();
				String[] ss = s.split("\\}\\|\\|\\|\\}");
				// 原始帖子
				String[] orginal = ss[0].split("\\|\\)\\)\\)\\|");
				String originUid = orginal[1];
				//System.out.println(originUid);
				String originTimeStamp = orginal[3];
				eachCaseCades.put(originUid, 0l);
				// 转发帖子
				String[] follow = ss[1].split("\\}\\]\\]\\]\\}");
				for (int i = 0; i < follow.length; i++) {
					String[] subFollow = follow[i].split(",");
					// System.out.println(subFollow.length );
					String followid = subFollow[1];
					String followTimeStamp = subFollow[15];
					// System.out.println(followid+"||"+followTimeStamp);
					long difference = Long.parseLong(followTimeStamp)
							- Long.parseLong(originTimeStamp);
					if (!eachCaseCades.containsKey(followid)) {
						eachCaseCades.put(followid, difference);
					} else {
						// 如果帖子已经存在，则判断时间的长短，来决定替换
						long tempDifference = eachCaseCades.get(followid);
						if (tempDifference > difference) {
							eachCaseCades.put(followid, difference);
						}
					}
				}
				//对casecade进行排序
				// 将Map转化为List集合，List采用ArrayList
				List<Map.Entry<String, Long>> list_Data = new ArrayList<Map.Entry<String, Long>>(eachCaseCades.entrySet());
				// 通过Collections.sort(List I,Comparator c)方法进行排序
				Collections.sort(list_Data, new Comparator<Map.Entry<String, Long>>() {

					@Override
					public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
						return o1.getValue().compareTo(o2.getValue());
					}
				});
				for(Map.Entry<String, Long> entry:list_Data){
					sBuilder.append(entry.getKey()+","+entry.getValue()+",");
				}
				sBuilder.append("\n");
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
		FileWriteUtil.WriteDocument("G:/zhd20160103/fo2.csv", sBuilder.toString());
	}

}

