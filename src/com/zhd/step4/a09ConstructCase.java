package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
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

//进行用户编号 并进行时间戳差值的计算后返回
public class a09ConstructCase {
	private static DecimalFormat df = new DecimalFormat("#.######");

	public static void main(String[] args) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		// 获得两个时间的毫秒时间差异最后一步过滤包含表情符的用户信息有：802886条
		System.out.println("开始运行");

		Date nowDate = new Date();
		// user_id_pair.txt belong.txt subgraph.txt
		// new a06FilterUidTimeStamp().operate(usernameid,
		// belong,merefollow1,casecade);
		// System.out.println("已完成");
		String precasecade = args[0];// precasecade.txt
		String casecade = args[1];// casecade.txt

		new a09ConstructCase().operate(precasecade, casecade);
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

	public void operate(String precasecade, String casecade) {
		BufferedReader br = null;
		FileReader reader = null;
		File file = null;
		file = new File(precasecade);
		int line = 0;
		// 对其进行重新编号
		Map<String, String> remaps = new LinkedHashMap<String, String>();

		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				String[] ss = str.split("\\}\\}");
				for (String subs : ss) {
					String[] strings = subs.split("\\|\\|");
					if (!remaps.containsKey(strings[0])) {
						remaps.put(strings[0], line + "");
						line++;
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
		// 输出来看下
		StringBuilder sBuilder = new StringBuilder();
//		for (Map.Entry<String, String> it : remaps.entrySet()) {
//			System.out.println(it.getKey() + "||" + it.getValue());
//			sBuilder.append(it.getValue() + "," + it.getValue() + "\r\n");
//		}
//		sBuilder.append("\r\n");
	// 进行替换编号
		Map<String, Long> eachCaseCades = null;
		double maxTimeStamp = 0d;
		//用于存储首节点与编号的映射
	
		try {
			//
			reader = new FileReader(new File(precasecade));
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				eachCaseCades = new LinkedHashMap<String, Long>();
				String[] ss = str.split("\\}\\}");
				// 原始帖子
				String[] orginal = ss[0].split("\\|\\|");
				String originUid = orginal[0];
				// 时间戳
				String originTimeStamp = orginal[1];
				eachCaseCades.put(remaps.get(originUid), 0l);
				
				// 转发的帖子
				for (int i = 1; i < ss.length; i++) {
					String[] subFollow = ss[i].split("\\|\\|");
					String followid = subFollow[0];
					String followTimeStamp = subFollow[1];
					// 与原始帖子的时间进行对比
					long difference = Long.parseLong(followTimeStamp)
							- Long.parseLong(originTimeStamp);
					// 判断级联是否存在该值
					if (!eachCaseCades.containsKey(remaps.get(followid))) {
						eachCaseCades.put(remaps.get(followid), difference);
					} else {
						// 存在取第一次被感染的节点
						// 如果帖子已经存在，则判断时间的长短，来决定替换
						long tempDifference = eachCaseCades.get(remaps
								.get(followid));
						if (tempDifference > difference) {
							eachCaseCades.put(remaps.get(followid), difference);
						}
					}
				}
				// 如果只有一个原始节点，则过滤掉
				if (eachCaseCades.size() < 2) {
					continue;
				}
				// 对casecade进行排序
				// 将Map转化为List集合，List采用ArrayList
				List<Map.Entry<String, Long>> list_Data = new ArrayList<Map.Entry<String, Long>>(
						eachCaseCades.entrySet());
				// 通过Collections.sort(List I,Comparator c)方法进行排序
				Collections.sort(list_Data,
						new Comparator<Map.Entry<String, Long>>() {
							@Override
							public int compare(Entry<String, Long> o1,
									Entry<String, Long> o2) {
								return o1.getValue().compareTo(o2.getValue());
							}
						});

				for (Map.Entry<String, Long> entry : list_Data) {
					// 进行以10为底的转换
					if (entry.getValue() != 0) {
						double temp = Math.log10(entry.getValue() / 1000);
						sBuilder.append(entry.getKey() + "," + df.format(temp)
								+ ",");
						if (maxTimeStamp < Double.valueOf(df.format(temp))) {
							maxTimeStamp =Double.valueOf(df.format(temp)) ;
						}
					} else {
						sBuilder.append(entry.getKey() + "," + entry.getValue()
								+ ",");
					}
				}
				sBuilder.replace(sBuilder.lastIndexOf(","),
						sBuilder.lastIndexOf(",") + 1, "");
				sBuilder.append("\r\n");
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
		sBuilder.append("最大的时间戳为：" + maxTimeStamp+"\n");
		sBuilder.append("节点的个数为："+remaps.size());
		FileWriteUtil.WriteDocument(casecade, sBuilder.toString());
		
	}
}
