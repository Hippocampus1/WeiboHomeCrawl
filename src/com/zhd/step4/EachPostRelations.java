package com.zhd.step4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.zhd.util.FileReadUtil;

/**
 * 
 * @author houdong
 *去除重复的帖子 这里的重复指的是帖子的id重复 但评论数 转发数 赞数可能不重复
 *结果获取	// 消息id 用户id 转发消息id 内容 发布时间
 */
public class EachPostRelations {
	public static void main(String[] args) {
		BufferedReader br = null;
		FileReader reader = null;
		//String dir = "G:/zhd20160103/";
		String dir="/mnt/disk/daeteam/zhd/data_processed_lcj/mess20160102/all/";
		File file = null;
		file = new File(dir + "uniqueemoticon.csv");
		List<String> alList = new ArrayList<String>();
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		FileWriter fw2 = null;
		BufferedWriter bw2 = null;
		PrintWriter pw2 = null;
		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			//原始的帖子
			fw = new FileWriter(new File(dir + "uniqueemoticonfilterOriginal1.csv"),
					true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			//被转发的帖子
			fw2 = new FileWriter(new File(dir + "uniqueemoticonfilterZhuanfa1.csv"),
					true);
			bw2 = new BufferedWriter(fw2);
			pw2 = new PrintWriter(bw2);
			int con = 0;
			String str = null;
			while ((str = br.readLine()) != null) {
				String[] ss = str.split("\\}\\|\\|\\|\\}");
				String mid = ss[0];
				if (!alList.contains(mid)) {
					con++;
					alList.add(mid);
					// 消息id 用户id 转发消息id 内容 发布时间
					if("".equals(ss[4])){
					pw.write(ss[0] + "}|||}" + ss[1] + "}|||}"
							+ ss[5] + "}|||}" + ss[9] + "\n");}
					else {
					pw2.write(ss[0] + "}|||}" + ss[1] + "}|||}" + ss[4] + "}|||}"
										+ ss[5] + "}|||}" + ss[9] + "\n");
						}
				}
				// System.out.println(str);
			}
			System.out.println(con);
		} catch (Exception e) {
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
