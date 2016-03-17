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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;
public class FilterPair {
	public static void main(String[] args) {
		// Map<String, Integer> maps = new HashMap<String, Integer>();
		String messDir = "/mnt/disk/daeteam/zhd/data_processed_lcj/messages/";
		String[] fileNameIn = new String[] { "unique1.csv", "unique2.csv",
				"unique3.csv", "unique4.csv", "unique5.csv", "unique6.csv" };
		// Map<String, String> msgIduserNameMaps=new LinkedHashMap<String,
		// String>();
		// StringBuilder strSubMess=null;
		BufferedReader br = null;
		FileReader reader = null;
		FileWriter fw1 = null;
		BufferedWriter bw1 = null;
		PrintWriter pw1 = null;// "J:\\user_id_pair.txt"
		FileWriter fw2 = null;
		BufferedWriter bw2 = null;
		PrintWriter pw2 = null;// "J:\\user_id_pair.txt"
		// Pattern p = Pattern.compile("\\[.{1,30}?\\]");// 最短匹配法
		String destFile1 = messDir + "msgidusername.csv";
		String destFile2 = messDir + "usernamemsgid.csv";
		for (int i = 0; i < 6; i++) {
			String fileName = messDir + fileNameIn[i];
			System.out.println(fileName);
			try {
				reader = new FileReader(new File(fileName));
				br = FileReadUtil.getReadStream(reader);
				fw1 = new FileWriter(new File(destFile1), true);
				bw1 = new BufferedWriter(fw1);
				pw1 = new PrintWriter(bw1);
				fw2 = new FileWriter(new File(destFile2), true);
				bw2 = new BufferedWriter(fw2);
				pw2 = new PrintWriter(bw2);
				String str = null;
				// strSubMess=new StringBuilder();
				while ((str = br.readLine()) != null) {
					String[] ss = str.split(",");
					if (ss.length != 16)
						continue;
	// 0消息ID,1用户ID,2用户名,3屏幕名,4转发消息ID,5消息内容,6消息URL,7来源,8图片URL,9音频URL,10视频URL,11地理坐标,12转发数,13评论数,14赞数,15发布时间
					pw1.write(ss[0] + "}||}" + ss[3]+"\n");
					if (!"".equals(ss[4])) {
						pw2.write(ss[3] + "}||}" + ss[4]+"\n");
					}

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (pw1 != null) {
					pw1.close();
				}
				if (bw1 != null) {
					try {
						bw1.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fw1 != null) {
					try {
						fw1.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (pw2 != null) {
						pw2.close();
					}
					if (bw2 != null) {
						try {
							bw2.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (fw2 != null) {
						try {
							fw2.close();
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

			}

		}
	}
}
 