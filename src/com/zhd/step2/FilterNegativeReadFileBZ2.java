package com.zhd.step2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import com.zhd.util.FileReadUtil;
/**
 * 
	* @ClassName: FilterNegativeReadFileBZ2 
	* @Description: 提取源数据中带有消极情感表情的帖子，输入:negative.txt;输出：  negative_emoticon.csv  
	* @author zeze
	* @date 2016年3月8日 上午10:20:21 
	*
 */
public class FilterNegativeReadFileBZ2 {
	public static void main(String[] args) {
		BufferedReader br=null;
		FileReader reader1=null;
		String srcdir="C:\\Users\\zeze\\Desktop\\毕业设计\\数据集\\";
		File file = null;
		file = new File(srcdir + "negative.txt");
		Set<String> set=new LinkedHashSet<String>();
		//StringBuffer sbBuffer=new StringBuffer();
		 try {
				reader1 = new FileReader(file);
				br = FileReadUtil.getReadStream(reader1);
				String str = null;
				while((str=br.readLine())!=null){
					set.add(str);	
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
				if (reader1 != null)
					try {
						reader1.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		 System.out.println("总共"+set.size()+"个表情符");
		 for(String ss:set){
			 System.out.println(ss);
		 }
		String dir = "C:\\Users\\zeze\\Desktop\\毕业设计\\sinaweb\\";
		String dir2 = "C:\\Users\\zeze\\Desktop\\毕业设计\\数据集\\";
		InputStream is = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		FileReader reader = null;
		int count = 1;
		for (int i = 11; i <= 11; i++) {
			String fileName1 = dir;
			// System.out.println(fileName1);

			File file1 = new File(fileName1);
			File[] files1 = file1.listFiles();
			for (File f1 : files1) {
				if (f1.getName().endsWith(".csv")) {
					 System.out.println(f1.getPath());
					// 读取
					try {
						fw = new FileWriter(new File(dir2 + "negative_emoticon" + ".csv"), true);
						bw = new BufferedWriter(fw);
						pw = new PrintWriter(bw);
						reader = new FileReader(new File(f1.getPath()));
						br = FileReadUtil.getReadStream(reader);
						String s = null;
					   // System.out.println(br.readLine());
						while ((s = br.readLine()) != null) {
							// System.out.println(s);
							String[] ss=s.split(",");
							if(ss.length!=16) continue;
							if(!"".equals(ss[4])){
								continue;
							}
							String content=ss[5];
							for(String emoction:set){
								if(content.contains(emoction)){
									pw.write(ss[2]+"}|||}"+ss[3]+"}|||}"+content
											+"}|||}"+ss[12]+"}|||}"+ss[13]+"}|||}"+ss[14]+"}|||}"+ss[15]+"\n");
									break;
								}
							}
							//pw.write(s + "\n");
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
							if (is != null)
								try {
									is.close();
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
			i++;
			//count++;
		}
	}
}