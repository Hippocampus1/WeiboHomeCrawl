package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;
/**
 * 对其进行合并
 * 源文件目录 +目标文件
 * @author houdong
 *
 */
public class a01MergeFollow {
	public static void main(String[] args) {
		    String srcFileName="I:\\毕业设计\\sinaweb\\";//args[0]
		    String destFileName="I:\\毕业设计\\数据集\\zeze.txt";//args[1]
			File file1 = new File(srcFileName);
			File[] files1 = file1.listFiles();
			BufferedReader br=null;
			FileReader reader=null;
			StringBuilder sbBuilder=new StringBuilder();
			int cnt=1;
			for (File f1 : files1) {
				cnt++;
				if(cnt>6)break;
				if (f1.getName().endsWith(".csv")) {
					System.out.println(f1.getName());
					try {
						
						reader = new FileReader(f1);
						br = FileReadUtil.getReadStream(reader);
						sbBuilder.append(f1.getName().replaceAll(".csv", "")+"}|||}");
						//************************************
						String s=null;
						while ((s = br.readLine()) != null) {
							
								sbBuilder.append(s+"}]]]}");
						}
							sbBuilder.append("\n");
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
				}
			}
			FileWriteUtil.WriteDocument(destFileName, sbBuilder.toString());
	}
}
