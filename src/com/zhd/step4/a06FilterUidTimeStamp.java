package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;

/**
 *进行最大子图的编号
 * 
 * @author Administrator
 *
 */
public class a06FilterUidTimeStamp {
	public static void main(String[] args) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		// 获得两个时间的毫秒时间差异最后一步过滤包含表情符的用户信息有：802886条
		System.out.println("开始运行");
		String usernameid="I:\\毕业设计\\数据集\\SubgraphProcess\\user_id_pair.txt";//args[0];
		String belong="I:\\毕业设计\\数据集\\Disjoint\\belong.txt";//args[1];
		String merefollow1="I:\\毕业设计\\数据集\\Disjoint\\subgraph.txt";//args[2];
		String casecade="I:\\毕业设计\\数据集\\Disjoint\\casecade.txt";//args[3];
		Date nowDate = new Date();
		//user_id_pair.txt belong.txt subgraph.txt
		new a06FilterUidTimeStamp().operate(usernameid, belong,merefollow1,casecade);
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
		System.out.println(day + "天" + hour + "小时" + min + "分钟" + sec + "秒");
	}

	public void operate(String usernameid, String belong,String  merefollow1,String casecade) {
		BufferedReader br = null;
		FileReader reader = null;
		
		File file = null;
		file = new File(usernameid);
		// 获取用户id及对应的名字
		Map<Integer, String> maps = new LinkedHashMap<Integer, String>();
		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				String[] ss = str.split("\\|\\|");
				maps.put(Integer.parseInt(ss[1]), ss[0]);
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
		file = new File(belong);
		// 存储最大子图的用户名
		List<String> alList = new ArrayList<String>();
		int line = 0;
		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				++line;
				if ("1".equals(str)) {
					alList.add(maps.get(line));
					// System.out.println(line);
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
		// 对其进行重新编号
		Map<String, String> remaps = new LinkedHashMap<String, String>();
		int cnt = 1;
		for (String uid : alList) {
			remaps.put(uid, cnt + "");
			cnt++;
		}
		
		// 进行替换编号
		StringBuilder sBuilder=new StringBuilder();
		Map<String, Long> eachCaseCades = null;
		try {
			//
			reader = new FileReader(new File(merefollow1));
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				eachCaseCades = new LinkedHashMap<String, Long>();
				String[] ss = str.split("\\}\\|\\|\\|\\}");
				// 原始帖子
				String[] orginal = ss[0].split("\\|\\)\\)\\)\\|");
				String originUid = orginal[1];
				
				if (remaps.containsKey(originUid)) {// 在最大子图中存在
					// 转发帖子
					String originTimeStamp = orginal[3];
					eachCaseCades.put(remaps.get(originUid), 0l);
					String[] follow = ss[1].split("\\}\\]\\]\\]\\}");
					for (int i = 0; i < follow.length; i++) {
						String[] subFollow = follow[i].split(",");
						// System.out.println(subFollow.length );
						String followid = subFollow[1];
						if (remaps.containsKey(followid)) {//判断是否该节点是否在最大子图中，若存在则进行映射
							String followTimeStamp = subFollow[15];
							// System.out.println(followid+"||"+followTimeStamp);
							long difference = Long.parseLong(followTimeStamp)
									- Long.parseLong(originTimeStamp);
							if (!eachCaseCades.containsKey(remaps.get(followid))) {
								eachCaseCades.put(remaps.get(followid), difference);
							} else {
								// 如果帖子已经存在，则判断时间的长短，来决定替换
								long tempDifference = eachCaseCades.get(remaps.get(followid));
								if (tempDifference > difference) {
									eachCaseCades.put(remaps.get(followid), difference);
								}
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
		FileWriteUtil.WriteDocument(casecade, sBuilder.toString());
	}
}
