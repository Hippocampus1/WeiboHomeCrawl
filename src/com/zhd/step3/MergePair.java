package com.zhd.step3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import com.zhd.util.FileReadUtil;

public class MergePair {
	public static void main(String[] args) {
 new MergePair().pairs();
	}

	public void pairs() {
		BufferedReader br = null;
		FileReader reader = null;
		
		String messDir = "/mnt/disk/daeteam/zhd/data_processed_lcj/messages/";
		String destFile1 = messDir + "uniquemsgidusername.csv";
		String destFile2 = messDir + "usernamemsgid.csv";
		//Map<String, String> msgIdUsernameMaps = new LinkedHashMap<String, String>();
		try {
			reader = new FileReader(new File(destFile1));
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			str = br.readLine();
			while ((str = br.readLine()) != null) {
				String[] ss = str.split("\\}\\|\\|\\}");
				// 消息id与用户名
				//msgIdUsernameMaps.put(ss[0], ss[1]);
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
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try {
			reader = new FileReader(new File(destFile2));
			br = FileReadUtil.getReadStream(reader);
			fw = new FileWriter(new File(messDir + "pair.csv"), true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			String str = null;
			// strSubMess=new StringBuilder();
			while ((str = br.readLine()) != null) {
				String[] ss = str.split("\\}\\|\\|\\}");
				String username=ss[0];
				String followId=ss[1];
//				if(msgIdUsernameMaps.containsKey(followId)){
//					pw.write(username+"||"+msgIdUsernameMaps.get(followId)+"\n");
//				}
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
		
		
		
		

	}
}
