package com.zhd.step3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;

/**
 * 
	* @ClassName: EmoticonExtractor 
	* @Description:  输入：unique1.csv,
	*                    unique2.csv,
	*                    unique3.csv,
	*                    unique4.csv,
	*                    unique5.csv,
	*                    unique6.csv
	*                输出：subgraph_mess1.csv,
	*                     subgraph_mess2.csv,
	*                     subgraph_mess3.csv,
	*                     subgraph_mess4.csv,
	*                     subgraph_mess5.csv,
	*                     subgraph_mess6.csv               
	* @author zeze
	* @date 2016年3月9日 上午10:37:47 
	*
 */
public class EmoticonExtractor {
	public static void main(String[] args) {
		Map<String, Integer> maps = new HashMap<String, Integer>();
		String messDir = "/mnt/disk/daeteam/zhd/data_processed_lcj/messages/";
		String[] fileNameIn = new String[] { "unique1.csv", "unique2.csv",
				"unique3.csv", "unique4.csv", "unique5.csv", "unique6.csv" };
		String[] fileNameOut = new String[] { "subgraph_mess1.csv",
				"subgraph_mess2.csv", "subgraph_mess3.csv",
				"subgraph_mess4.csv", "subgraph_mess5.csv",
				"subgraph_mess6.csv" };
		// StringBuilder strSubMess=null;
		BufferedReader br = null;
		FileReader reader = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;// "J:\\user_id_pair.txt"
		Pattern p = Pattern.compile("\\[.{1,30}?\\]");// 最短匹配法

		for (int i = 0; i < 6; i++) {
			String fileName = messDir + fileNameIn[i];
			String destFile = messDir + "submess/" + fileNameOut[i];
			// file=new File(fileName);
			try {
				reader = new FileReader(new File(fileName));
				br = FileReadUtil.getReadStream(reader);
				fw = new FileWriter(new File(destFile), true);
				bw = new BufferedWriter(fw);
				pw = new PrintWriter(bw);
				String str = null;
				// strSubMess=new StringBuilder();
				while ((str = br.readLine()) != null) {
					String[] ss = str.split(",");
					if (ss.length != 16)
						continue;
					if (!"".equals(ss[4])) {
						continue;
					}
					String content = ss[5];
					Matcher m = p.matcher(content);
					int flag=0;
					while (m.find()) {
						flag=1;
						// System.out.println(m.group());
						String ss1 = m.group();
						if (!maps.containsKey(ss1)) {
							maps.put(ss1, 1);
						} else {
							maps.put(ss1, maps.get(ss1) + 1);
						}
					}
					if(flag==1){
						pw.write(str+"\n");
					}
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fw != null) {
					try {
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
			}

			// 将Map转化为List集合，List采用ArrayList
			List<Map.Entry<String, Integer>> list_Data = new ArrayList<Map.Entry<String, Integer>>(
					maps.entrySet());
			// 通过Collections.sort(List I,Comparator c)方法进行排序
			Collections.sort(list_Data,
					new Comparator<Map.Entry<String, Integer>>() {

						@Override
						public int compare(Entry<String, Integer> o1,
								Entry<String, Integer> o2) {
							return (o2.getValue() - o1.getValue());
						}
					});
			StringBuilder sbBuilder=new StringBuilder();
			for (Map.Entry<String, Integer> it : list_Data) {
				System.out.println(it.getKey() + "==>" + it.getValue());
			    sbBuilder.append(it.getKey()+"||"+it.getValue()+"\n");
			}
			String fileNameCount=messDir+"emoctionCount.txt";
			FileWriteUtil.WriteDocument(fileNameCount, sbBuilder.toString());
		}
	}
}
 