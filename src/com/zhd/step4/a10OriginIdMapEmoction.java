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

//用户的编号=》情绪值   用户的编号=》重新编号
public class a10OriginIdMapEmoction {

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
		String lineFile = args[0];// 用户id=》进行重新编号
		String casecadeemoction= args[1];// casecade.txt
		String lineemoction=args[2];//
		new a10OriginIdMapEmoction().operate(lineFile,casecadeemoction,lineemoction);
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

	public void operate(String lineFile, String casecadeemoction,
			String lineemoction) {
		BufferedReader br = null;
		FileReader reader = null;
		File file = null;
		
		// 用户id与情感值
		file = new File(casecadeemoction);
		// lineid=>情感值
		Map<String, String> lineidEmoctionMaps = new LinkedHashMap<String, String>();
		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			int line=1;
			while ((str = br.readLine()) != null) {
				String[] ss=str.split("\\|\\|");
				lineidEmoctionMaps.put(line+"", ss[1]);
				line++;
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
		
		file = new File(lineFile);
		//id=》对应于行数
		//对用户id=》编号进行遍历
				StringBuilder sBuilder=new StringBuilder();
		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				sBuilder.append(str+"||"+lineidEmoctionMaps.get(str)+"\n");
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

		FileWriteUtil.WriteDocument(lineemoction, sBuilder.toString());
		
	}
}
