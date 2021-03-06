package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;
//对全部级联中的用户进行过滤 并提取级联用户id及相应的时间戳，以及最后的情感极性,主要过滤出现用户的个数
public class a07FilterMergeFollow {
	
	// 获取转发关系
	public static void main(String[] args) {
		
		String srcFileName = args[0];// "G:/zhd20160103/fo1.csv"
		String destFileName = args[1];// "G:/zhd20160103/re.csv"
		String userCounts = args[2];
		String emoctionFile=args[3];
		String caseEmotionFile=args[4];//级联文件
		BufferedReader br = null;
		FileReader reader = null;
		//取出表情符和极性
		Map<String,Integer> emoctionsMap=new LinkedHashMap<String, Integer>();
		try {
			reader = new FileReader(
					new File(emoctionFile));
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				String ss[]=str.split("\\|\\|");
				emoctionsMap.put(ss[0],Integer.parseInt(ss[1]));
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
		
		// 统计用户出现的次数
		Map<String, Integer> userCountMaps = new LinkedHashMap<String, Integer>();
		try {
			reader = new FileReader(new File(srcFileName));
			br = FileReadUtil.getReadStream(reader);
			// ************************************
			String s = null;
			while ((s = br.readLine()) != null) {
				String[] ss = s.split("\\}\\|\\|\\|\\}");
				// 原始帖子
				String[] orginal = ss[0].split("\\|\\)\\)\\)\\|");
				String originUid = orginal[1];
				// 如果不包含
				if (!userCountMaps.containsKey(originUid)) {
					userCountMaps.put(originUid, 1);
				} else {// 如果包含
					userCountMaps.put(originUid,
							userCountMaps.get(originUid) + 1);
				}
				// 转发帖子
				String[] follow = ss[1].split("\\}\\]\\]\\]\\}");
				for (int i = 0; i < follow.length; i++) {
					String[] subFollow = follow[i].split(",");
					// System.out.println(subFollow.length );
					String followuid = subFollow[1];
					// 如果不包含
					if (!userCountMaps.containsKey(followuid)) {
						userCountMaps.put(followuid, 1);
					} else {// 如果包含
						userCountMaps.put(followuid,
								userCountMaps.get(followuid) + 1);
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
		
		// 获取用户在全部级联中出现的次数要大于多少,并用set进行存储
		int usercounts = Integer.parseInt(userCounts);
		System.out.println(usercounts);
		Set<String> set = new LinkedHashSet<String>();
		for (Map.Entry<String, Integer> it : userCountMaps.entrySet()) {
			if (it.getValue() >= usercounts) {
				set.add(it.getKey());
			}
		}
		//System.out.println("超过20的有："+set.size());
		// 再次遍历
		StringBuilder sBuilder = new StringBuilder();//存储级联
		StringBuilder emoctionSbBuilder=new StringBuilder();//存储级联的表情极性
		StringBuilder subsBuilder = null;
		try {
			reader = new FileReader(new File(srcFileName));
			br = FileReadUtil.getReadStream(reader);
			// ************************************
			String s = null;

			while ((s = br.readLine()) != null) {
				int sum=0;//用于累加情绪
				String[] ss = s.split("\\}\\|\\|\\|\\}");
				// 原始帖子
				String[] orginal = ss[0].split("\\|\\)\\)\\)\\|");
				String originUid = orginal[1];
				//进行一次表情符的遍历
				for(Map.Entry<String,Integer> it:emoctionsMap.entrySet()){
					if(orginal[2].indexOf(it.getKey())!=-1){
						sum+=it.getValue();
					}
				}
//				if (!set.contains(originUid)) {
//					continue;
//				}
				// 转发帖子
				String[] follow = ss[1].split("\\}\\]\\]\\]\\}");
				subsBuilder = new StringBuilder();// 存储子集
				int flag = 0;// 标识是否存在子集
				for (int i = 0; i < follow.length; i++) {
					String[] subFollow = follow[i].split(",");
					// System.out.println(subFollow.length );
					String followuid = subFollow[1];
					if (set.contains(followuid)) {
						flag = 1;
						subsBuilder.append(followuid + "||" + subFollow[15]+"}}");
					}
				}
				if (flag == 1) {
					sBuilder.append(originUid + "||" + orginal[3] + "}}"
							+ subsBuilder.toString() +"\n");
					emoctionSbBuilder.append(originUid + "||" +sum+"\n");
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
		FileWriteUtil.WriteDocument(destFileName, sBuilder.toString());
		FileWriteUtil.WriteDocument(caseEmotionFile, emoctionSbBuilder.toString());
	}
	
}
