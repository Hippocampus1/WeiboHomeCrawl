package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;

public class a03RelationShip {

	// 获取转发关系
	public static void main(String[] args) {
		String srcFileName = args[0];// "G:/zhd20160103/fo1.csv"
		String destFileName = args[1];// "G:/zhd20160103/re.csv"
		String followNums = args[2];// 统计转发的数目
		BufferedReader br = null;
		FileReader reader = null;
		StringBuilder sBuilder = new StringBuilder();
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
				// 转发帖子
				String[] follow = ss[1].split("\\}\\]\\]\\]\\}");
				if (follow.length < Integer.parseInt(followNums))
					continue;
				for (int i = 0; i < follow.length; i++) {
					String[] subFollow = follow[i].split(",");
					// System.out.println(subFollow.length );
					String followuid = subFollow[1];
					sBuilder.append(originUid + "||" + followuid + "\n");
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
