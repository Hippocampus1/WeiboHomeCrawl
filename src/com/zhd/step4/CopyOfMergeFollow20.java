package com.zhd.step4;

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
 * 对其进行合并
 * 源文件目录 +目标文件
 * @author houdong
 *
 */
public class CopyOfMergeFollow20 {
	public static void main(String[] args) {
		String srcFileName=args[0];
		String destFileName=args[1];
			File file1 = new File(srcFileName);
			File[] files1 = file1.listFiles();
			BufferedReader br=null;
			FileReader reader=null;
			 FileWriter fw = null;
				BufferedWriter bw = null;
				PrintWriter pw = null;
			for (File f1 : files1) {
				if (f1.getName().endsWith(".csv")) {
					try {
						
						reader = new FileReader(f1);
						br = FileReadUtil.getReadStream(reader);
						fw = new FileWriter(new File(destFileName), true);
						bw = new BufferedWriter(fw);
						pw = new PrintWriter(bw);
						//************************************
						String s=null;
						pw.write(f1.getName().replaceAll(".csv", "")+"}|||}");
						while ((s = br.readLine()) != null) {
							
								pw.write(s+"}}}}");	
						}
							pw.write("\n");
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
	}
}
