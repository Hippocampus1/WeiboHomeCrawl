package com.zhd.step4;

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
//对级联数也进行过滤
public class a08FilterCasecade {
	public static void main(String[] args) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		// 获得两个时间的毫秒时间差异最后一步过滤包含表情符的用户信息有：802886条
		System.out.println("开始运行");
		
		Date nowDate = new Date();
		//user_id_pair.txt belong.txt subgraph.txt
	//	new a06FilterUidTimeStamp().operate(usernameid, belong,merefollow1,casecade);
		// System.out.println("已完成");
		String precasecade=args[0];//precasecade.txt
		String casecade=args[1];//casecade.txt
		String lineFile=args[3];//通过行来进行存储过滤每个级联的大小的的行号，最后目的用于匹配极性值
		int num=Integer.parseInt(args[2]);//级联的个数
		new a08FilterCasecade().operate(precasecade, casecade,num,lineFile);
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

	public void operate(String precasecade, String casecade,int num,String lineFile) {
		BufferedReader br = null;
		FileReader reader = null;
		StringBuilder sBuilder=new StringBuilder();
		StringBuilder sBuilder2=new StringBuilder();
		Map<String, Long> eachCaseCades = null;
		try {
			//进行级联个数的过滤
			eachCaseCades = new LinkedHashMap<String, Long>();
			reader = new FileReader(new File(precasecade));
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			int line=0;
			
			while ((str = br.readLine()) != null) {
				line++;//获取行
				eachCaseCades = new LinkedHashMap<String, Long>();
				String[] ss = str.split("\\}\\}");
				// 原始帖子
				String[] orginal = ss[0].split("\\|\\|");
				String originUid = orginal[0];
				// 时间戳
				long originTimeStamp = Long.parseLong(orginal[1]);
				eachCaseCades.put(originUid,originTimeStamp);
				// 转发的帖子
				for (int i = 1; i < ss.length; i++) {
					String[] subFollow = ss[i].split("\\|\\|");
					String followid = subFollow[0];
					long followTimeStamp = Long.parseLong(subFollow[1]);
				
					// 判断级联是否存在该值
					if (!eachCaseCades.containsKey(followid)) {
						eachCaseCades.put(followid,followTimeStamp);
					} else {
						// 存在取第一次被感染的节点
						// 如果帖子已经存在，则判断时间的长短，来决定替换
						long tempDifference = eachCaseCades.get(followid);
						if (tempDifference > followTimeStamp) {
							eachCaseCades.put(followid,
									followTimeStamp);
						}
					}
				}
				if(eachCaseCades.size()>=num){
					for(Map.Entry<String,Long> entry:eachCaseCades.entrySet()){
						sBuilder.append(entry.getKey() + "||" + entry.getValue()
								+ "}}");
					}
					//sBuilder.replace(sBuilder.lastIndexOf("}}"),sBuilder.lastIndexOf("}}")+2, "");
					sBuilder.append("\r\n");
					sBuilder2.append(line+"\n");
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
		FileWriteUtil.WriteDocument(lineFile, sBuilder2.toString());
	}
}
