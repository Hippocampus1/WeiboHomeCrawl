package com.zeze.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.zhd.util.FileReadUtil;

/**
 * 
	* @ClassName: FilterPair(从数据集中分离屏幕名发布的消息ID，分离屏幕名转发的消息的ID)
	* @Description:  输入：原始数据集,
	*                输出：msgidusername.csv(消息ID}||}屏幕名);
	*                    usernamemsgid.csv(屏幕名}||}转发消息)
	* @author zeze
	* @date 2016年3月8日 下午4:39:19 
	*
 */

public class FilterPair {
	public static void main(String[] args) {
		// Map<String, Integer> maps = new HashMap<String, Integer>();
		String messDir = "I:\\毕业设计\\sinaweb\\";
		String destDir = "I:\\毕业设计\\数据集\\";
		String[] fileNameIn = new String[] { "czz-125632-2016-03-04-12-23-08-result.weibo2.msg.csv", "czz-125644-2016-03-04-13-24-10-result.weibo2.msg.csv", "czz-125653-2016-03-04-14-30-36-result.weibo2.msg.csv",
				"czz-125661-2016-03-04-17-34-52-result.weibo2.msg.csv", "czz-125683-2016-03-04-19-01-41-result.weibo2.msg.csv", "czz-125694-2016-03-04-22-05-07-result.weibo2.msg.csv", "czz-125723-2016-03-04-23-00-26-result.weibo2.msg.csv",
				"czz-125748-2016-03-05-02-10-40-result.weibo2.msg.csv", "czz-125756-2016-03-05-04-27-43-result.weibo2.msg.csv", "czz-125771-2016-03-05-05-50-29-result.weibo2.msg.csv", "czz-125781-2016-03-05-06-56-27-result.weibo2.msg.csv",
				"czz-125787-2016-03-05-07-54-07-result.weibo2.msg.csv", "czz-125796-2016-03-05-10-39-52-result.weibo2.msg.csv", "czz-125812-2016-03-05-11-33-16-result.weibo2.msg.csv", "czz-125816-2016-03-05-12-31-55-result.weibo2.msg.csv",
				"czz-125823-2016-03-05-14-45-22-result.weibo2.msg.csv", "czz-125841-2016-03-05-16-49-51-result.weibo2.msg.csv", "czz-125859-2016-03-05-18-15-00-result.weibo2.msg.csv", "czz-125867-2016-03-05-20-06-10-result.weibo2.msg.csv",
				"czz-125881-2016-03-05-21-34-54-result.weibo2.msg.csv", "czz-125902-2016-03-05-22-59-54-result.weibo2.msg.csv", "czz-125909-2016-03-05-23-47-41-result.weibo2.msg.csv", "czz-125930-2016-03-06-00-39-10-result.weibo2.msg.csv",
				"czz-125935-2016-03-06-02-16-11-result.weibo2.msg.csv", "czz-125951-2016-03-06-03-24-35-result.weibo2.msg.csv", "czz-125960-2016-03-06-05-25-57-result.weibo2.msg.csv", "czz-125972-2016-03-06-06-45-35-result.weibo2.msg.csv",
				"czz-125989-2016-03-06-08-33-50-result.weibo2.msg.csv", "czz-126000-2016-03-06-10-05-15-result.weibo2.msg.csv", "czz-126014-2016-03-06-11-45-56-result.weibo2.msg.csv", "czz-126025-2016-03-06-13-13-04-result.weibo2.msg.csv",
				"czz-126035-2016-03-06-14-30-41-result.weibo2.msg.csv", "czz-126045-2016-03-06-16-17-20-result.weibo2.msg.csv", "czz-126069-2016-03-06-17-50-13-result.weibo2.msg.csv", "czz-126077-2016-03-06-19-54-49-result.weibo2.msg.csv",
				"czz-126095-2016-03-06-21-00-29-result.weibo2.msg.csv", "czz-126100-2016-03-06-23-09-06-result.weibo2.msg.csv", "czz-126116-2016-03-07-00-11-18-result.weibo2.msg.csv", "czz-126126-2016-03-07-01-20-41-result.weibo2.msg.csv",
				"czz-126135-2016-03-07-03-03-37-result.weibo2.msg.csv", "czz-126144-2016-03-07-04-21-55-result.weibo2.msg.csv", "czz-126158-2016-03-07-05-28-11-result.weibo2.msg.csv", "czz-126167-2016-03-07-06-10-20-result.weibo2.msg.csv",
				"czz-126174-2016-03-07-07-05-55-result.weibo2.msg.csv", "czz-126179-2016-03-07-08-14-12-result.weibo2.msg.csv", "czz-126189-2016-03-07-10-07-31-result.weibo2.msg.csv", "czz-126200-2016-03-07-11-25-36-result.weibo2.msg.csv",
				"czz-126223-2016-03-07-13-12-16-result.weibo2.msg.csv", "czz-126276-2016-03-07-14-59-50-result.weibo2.msg.csv", "czz-126290-2016-03-07-16-30-09-result.weibo2.msg.csv", "czz-126311-2016-03-07-18-21-02-result.weibo2.msg.csv",
				"czz-126329-2016-03-07-19-37-46-result.weibo2.msg.csv", "czz-126337-2016-03-07-21-30-51-result.weibo2.msg.csv", "czz-126358-2016-03-07-22-37-01-result.weibo2.msg.csv", "czz-126365-2016-03-08-00-22-52-result.weibo2.msg.csv",
				"czz-126381-2016-03-08-01-38-10-result.weibo2.msg.csv", "czz-126393-2016-03-08-02-51-58-result.weibo2.msg.csv", "czz-126404-2016-03-08-04-22-19-result.weibo2.msg.csv", "czz-126416-2016-03-08-05-45-09-result.weibo2.msg.csv",
				"czz-126431-2016-03-08-06-56-28-result.weibo2.msg.csv", "czz-126451-2016-03-08-08-57-11-result.weibo2.msg.csv", "fyl_lps-124751-2016-03-08-08-32-32-result.weibo2.msg.csv", "fzuzeze-125728-2016-03-04-23-52-49-result.weibo2.msg.csv",
				"fzuzeze-125785-2016-03-05-07-50-49-result.weibo2.msg.csv", "fzuzeze-125803-2016-03-05-09-56-01-result.weibo2.msg.csv", "fzuzeze-125807-2016-03-05-11-38-47-result.weibo2.msg.csv", "fzuzeze-125817-2016-03-05-13-06-31-result.weibo2.msg.csv",
				"fzuzeze-125846-2016-03-05-16-29-17-result.weibo2.msg.csv", "fzuzeze-125875-2016-03-05-20-51-25-result.weibo2.msg.csv", "fzuzeze-125906-2016-03-05-23-10-34-result.weibo2.msg.csv", "fzuzeze-125957-2016-03-06-04-24-42-result.weibo2.msg.csv",
				"fzuzeze-125990-2016-03-06-09-18-46-result.weibo2.msg.csv", "fzuzeze-126006-2016-03-06-10-00-56-result.weibo2.msg.csv", "fzuzeze-126012-2016-03-06-11-29-36-result.weibo2.msg.csv", "fzuzeze-126023-2016-03-06-12-50-30-result.weibo2.msg.csv",
				"fzuzeze-126079-2016-03-06-19-47-00-result.weibo2.msg.csv", "fzuzeze-126093-2016-03-06-20-52-15-result.weibo2.msg.csv", "hz-125642-2016-03-04-13-07-38-result.weibo2.msg.csv", "hz-125649-2016-03-04-14-15-25-result.weibo2.msg.csv",
				"hz-125659-2016-03-04-14-56-02-result.weibo2.msg.csv", "hz-125666-2016-03-04-16-08-49-result.weibo2.msg.csv", "hz-125677-2016-03-05-12-11-51-result.weibo2.msg.csv", "hz-125821-2016-03-05-14-14-19-result.weibo2.msg.csv",
				"hz-125837-2016-03-05-16-02-43-result.weibo2.msg.csv", "hz-125850-2016-03-05-18-16-06-result.weibo2.msg.csv", "hz-125868-2016-03-05-19-42-11-result.weibo2.msg.csv", "hz-125879-2016-03-05-20-16-57-result.weibo2.msg.csv",
				"hz-125883-2016-03-05-21-43-27-result.weibo2.msg.csv", "hz-125903-2016-03-05-23-14-43-result.weibo2.msg.csv", "hz-125911-2016-03-06-01-43-06-result.weibo2.msg.csv", "hz-125948-2016-03-06-02-49-29-result.weibo2.msg.csv",
				"hz-125955-2016-03-06-04-21-51-result.weibo2.msg.csv", "hz-125966-2016-03-06-05-26-32-result.weibo2.msg.csv", "hz-125973-2016-03-06-07-32-29-result.weibo2.msg.csv", "hz-125995-2016-03-06-08-56-06-result.weibo2.msg.csv",
				"hz-126003-2016-03-06-10-17-43-result.weibo2.msg.csv", "hz-126016-2016-03-06-11-17-33-result.weibo2.msg.csv", "hz-126021-2016-03-06-12-07-41-result.weibo2.msg.csv", "hz-126028-2016-03-06-13-34-27-result.weibo2.msg.csv",
				"hz-126038-2016-03-06-14-43-26-result.weibo2.msg.csv", "hz-126047-2016-03-06-16-07-02-result.weibo2.msg.csv", "hz-126066-2016-03-06-17-21-22-result.weibo2.msg.csv", "hz-126074-2016-03-06-18-21-00-result.weibo2.msg.csv",
				"hz-126080-2016-03-06-19-15-30-result.weibo2.msg.csv", "hz-126084-2016-03-06-21-11-38-result.weibo2.msg.csv", "hz-126101-2016-03-06-22-12-37-result.weibo2.msg.csv", "hz-126109-2016-03-06-23-56-57-result.weibo2.msg.csv",
				"hz-126125-2016-03-07-01-12-11-result.weibo2.msg.csv", "hz-126134-2016-03-07-02-44-13-result.weibo2.msg.csv", "hz-126142-2016-03-07-04-02-38-result.weibo2.msg.csv", "hz-126154-2016-03-07-05-29-13-result.weibo2.msg.csv",
				"hz-126168-2016-03-07-07-14-24-result.weibo2.msg.csv", "hz-126181-2016-03-07-08-24-31-result.weibo2.msg.csv", "hz-126192-2016-03-07-11-28-10-result.weibo2.msg.csv", "hz-126224-2016-03-07-12-59-45-result.weibo2.msg.csv",
				"hz-126275-2016-03-07-13-56-49-result.weibo2.msg.csv", "hz-126284-2016-03-07-14-59-33-result.weibo2.msg.csv", "joyce-125618-2016-03-04-11-14-25-result.weibo2.msg.csv", "joyce-125633-2016-03-05-10-20-07-result.weibo2.msg.csv",
				"joyce-125900-2016-03-05-22-07-40-result.weibo2.msg.csv", "joyce-125905-2016-03-05-23-17-06-result.weibo2.msg.csv", "joyce-126190-2016-03-07-17-16-20-result.weibo2.msg.csv", "ProryGao-125792-2016-03-05-09-17-05-result.weibo2.msg.csv",
				"yeomanwhu-126418-2016-03-08-05-21-06-result.weibo2.msg.csv" };
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
		String destFile1 = destDir + "msgidusername.csv";
		String destFile2 = destDir + "usernamemsgid.csv";
		for (int i = 0; i < fileNameIn.length; i++) {
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
					// 消息ID}||}屏幕名
					if (!"".equals(ss[3])){//屏幕名不为空
						pw1.write(ss[0] + "}||}" + ss[3] + "\n");
					}
					// 屏幕名}||}转发消息
					if (!"".equals(ss[4])) {//转发ID不为空
						pw2.write(ss[3] + "}||}" + ss[4] + "\n");
					}

				}

			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if (pw1 != null) {
					pw1.close();
				}
				if (bw1 != null) {
					try {
						bw1.close();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fw1 != null) {
					try {
						fw1.close();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (pw2 != null) {
						pw2.close();
					}
					if (bw2 != null) {
						try {
							bw2.close();
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (fw2 != null) {
						try {
							fw2.close();
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (br != null)
							try {
								br.close();
							}
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						if (reader != null)
							try {
								reader.close();
							}
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}

			}

		}
	}
}
