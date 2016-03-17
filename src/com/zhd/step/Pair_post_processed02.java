package com.zhd.step;
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



public class Pair_post_processed02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		Date nowDate = new Date();
		BufferedReader br = null;
		FileReader reader = null;
		//File file = new File("J:\\pair.txt");
		File file = new File(args[0]);
		Map<String, Long> maps = new LinkedHashMap<String, Long>();
		StringBuilder sBuilderIdPair = new StringBuilder();
		StringBuilder sBuilderIdName = new StringBuilder();
		long total = 0;
		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			long cnt = 0;
			// 用来存储用户的id

			// System.err.println(maps.get("hh"));

			while ((str = br.readLine()) != null) {
				String[] temp = str.split("%&%");
				long x, y;
				if (!maps.containsKey(temp[0])) {
					maps.put(temp[0], ++cnt);
					x = cnt;
				} else {
					x = maps.get(temp[0]);
				}
				if (!maps.containsKey(temp[1])) {
					maps.put(temp[1], ++cnt);
					y = cnt;
				} else {
					y = maps.get(temp[1]);
				}
				// System.out.println(x+"==>"+y);
				sBuilderIdPair.append(x + "||" + y + "\n");
			}
			// 遍历maps
			for (Map.Entry<String, Long> entry : maps.entrySet()) {
				// System.out.println("key= " + entry.getKey() + " and value= "
				// + entry.getValue());
				sBuilderIdName.append(entry.getKey() + "||" + entry.getValue() + "\n");
			}
			total = maps.size();
			maps.clear();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
//		FileWriteUtil.WriteDocument("J:\\user_id_pair.txt", sBuilderIdPair.toString());
//		FileWriteUtil.WriteDocument("J:\\user_name_id.txt", sBuilderIdName.toString());
//		FileWriteUtil.WriteDocument("J:\\user_count.txt", String.valueOf(total));
		FileWriteUtil.WriteDocument(args[1], sBuilderIdPair.toString());
		FileWriteUtil.WriteDocument(args[2], sBuilderIdName.toString());
		FileWriteUtil.WriteDocument(args[3], String.valueOf(total));
		System.out.println("已完成");
		Date endDate = new Date();
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		System.out.println(day + "天" + hour + "小时" + min + "分钟");
	}
}
