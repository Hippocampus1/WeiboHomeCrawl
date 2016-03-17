package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;
/**
 * 通过原始帖子ID与转发帖子对应的转发id连接起来
 * @author houdong
 *
 */
public class FilterFollow {
	public static void main(String[] args) {
		/**
		 * 读取原始带有表情的帖子（非转发的）
		 */
		BufferedReader br=null;
		FileReader reader1=null;
		String srcdir="/mnt/disk/daeteam/zhd/data_processed_lcj/mess20160102/all/";
		File file = null;
		file = new File(srcdir + "uniqueemoticonfilterOriginal1.csv");
		Map<String,String> originMaps=new LinkedHashMap<String,String>();
		 try {
				reader1 = new FileReader(file);
				br = FileReadUtil.getReadStream(reader1);
				String str = null;
				while((str=br.readLine())!=null){
					String[] ss = str.split("\\}\\|\\|\\|\\}");
					//	System.out.println(str);
						//mid+{uid+content+timestamp}
						originMaps.put(ss[0], str);
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
//		 System.out.println("总共"+set.size()+"个表情符");
//		 for(String ss:set){
//			 System.out.println(ss);
//		 }
		String dir = "/mnt/disk/liushenghua/sinawb201311to201402/";//读取文件
		InputStream is = null;
		String destDir="/mnt/disk/daeteam/zhd/data_processed_lcj/mess20160102/all/follow/";
		BufferedReader reader = null;
		int p=1;
	//	StringBuilder relationship=new StringBuilder();
		for (int i = p; i <=12; i++) {
			String fileName1 = dir + "bz2_d" + i + "/";
			// System.out.println(fileName1);

			File file1 = new File(fileName1);
			File[] files1 = file1.listFiles();
			for (File f1 : files1) {
				if (f1.getName().endsWith(".bz2")) {
					 System.out.println(f1.getPath());
					// 读取压缩文件.bz
					 //0消息ID,1用户ID,2用户名,3屏幕名,4转发消息ID,5消息内容,6消息URL,7来源,8图片URL,9音频URL,10视频URL,11地理坐标,12转发数,13评论数,14赞数,15发布时间
					try {
						
						//************************************
						is = new BZip2CompressorInputStream(
								new FileInputStream(new File(f1.getPath())));
						reader = new BufferedReader(new InputStreamReader(is));
						String s = null;
					 System.out.println(reader.readLine());
						while ((s = reader.readLine()) != null) {
							// System.out.println(s);
							String[] ss=s.split(",");
							if(ss.length!=16) continue;
                             if(!"".equals(ss[4])){//
								if(originMaps.containsKey(ss[4])){
									FileWriteUtil.WriteDocument(destDir+ss[4]+".csv", s+"\n");
								}		
							}
									
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
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
			i++;
			String fileName2 = dir + "bz2_d" + i + "/";
			// System.out.println(fileName2);
			File file2 = new File(fileName2);
			File[] files2 = file2.listFiles();
			for (File f2 : files2) {
				if (f2.getName().endsWith(".bz2")) {
					 System.out.println(f2.getPath());
					// 读取
					try {
					
						//*******************************************
						is = new BZip2CompressorInputStream(
								new FileInputStream(new File(f2.getPath())));
						reader = new BufferedReader(new InputStreamReader(is));
						System.out.println(	reader.readLine());
						String s = null;
						while ((s = reader.readLine()) != null) {
							// System.out.println(s);
						//	pw.write(s + "\n");
							String[] ss=s.split(",");
							if(ss.length!=16) continue;
							 if(!"".equals(ss[4])){//
									if(originMaps.containsKey(ss[4])){
										FileWriteUtil.WriteDocument(destDir+ss[4]+".csv", s+"\n");
									}		
							}
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						
						
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
		//	count++;
		}
	}
}
