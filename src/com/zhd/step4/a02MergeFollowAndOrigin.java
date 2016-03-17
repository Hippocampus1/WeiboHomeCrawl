package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;

/**
 * 将原始的帖子和转发帖子进行合并
 * 
 * @author houdong
 *
 */
public class a02MergeFollowAndOrigin {
	public static void main(String[] args) {
		// uniqueemoticonfilterOriginal1.csv;merefollow.csv;merefollow1.csv
		String originFileName = args[0];
		String srcFileName = args[1];
		String destFileName = args[2];
		BufferedReader br = null;
		FileReader reader = null;
		Map<String, String> originIdTimeStampsMap = new LinkedHashMap<String, String>();
		try {
			reader = new FileReader(new File(originFileName));
			br = FileReadUtil.getReadStream(reader);
			// ************************************
			String s = null;
			while ((s = br.readLine()) != null) {
				String[] ss = s.split("\\}\\|\\|\\|\\}");
				originIdTimeStampsMap.put(ss[0], ss[1] + "|)))|" + ss[2] + "|)))|" + ss[3]);
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
		System.out.println(originIdTimeStampsMap.size());
		StringBuilder sBuilder = new StringBuilder();
		try {
			reader = new FileReader(new File(srcFileName));
			br = FileReadUtil.getReadStream(reader);
			// ************************************
			String s = null;
			while ((s = br.readLine()) != null) {

				String[] strings = s.split("\\}\\|\\|\\|\\}");
				sBuilder.append(strings[0] + "|)))|" + originIdTimeStampsMap.get(strings[0]) + "}|||}" + strings[1] + "\n");

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
		FileWriteUtil.WriteDocument(destFileName, sBuilder.toString());
	}

}
