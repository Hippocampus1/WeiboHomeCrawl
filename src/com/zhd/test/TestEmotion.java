package com.zhd.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;

/**
 * 
	* @ClassName: TestEmotion 
	* @Description:  TXT文件读取表情字符转存至csv文件中，注意TXT编码为utf-8
	* @author zeze
	* @date 2016年3月2日 下午2:49:46 
	*
 */
public class TestEmotion {
	public static void main(String[] args) {
		BufferedReader br = null;
		FileReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new FileReader(
					new File(
							"C:\\Users\\zeze\\Desktop\\毕业设计\\数据集\\positive.txt"));
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str + "||" + "1"+"\n");
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
		try {
			reader = new FileReader(
					new File(
							"C:\\Users\\zeze\\Desktop\\毕业设计\\数据集\\negative.txt"));
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str + "||" + "-1"+"\n");
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
		System.out.println(sb.toString());
		FileWriteUtil.WriteDocument("C:\\Users\\zeze\\Desktop\\毕业设计\\数据集\\allemotion.csv", sb.toString());
	}
	
}
