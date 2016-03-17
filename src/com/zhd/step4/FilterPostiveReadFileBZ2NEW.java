package com.zhd.step4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.compress.compressors.bzip2.*;

import com.zhd.util.FileReadUtil;
/**
 * 读取正面积极的帖子
 * @author houdong
 *
 */
public class FilterPostiveReadFileBZ2NEW {
	public static void main(String[] args) {
		/**
		 * 读取正面表情符号
		 */
		BufferedReader br=null;
		FileReader reader1=null;
		String srcdir="/mnt/disk/daeteam/zhd/data_processed_lcj/mess20160102/";
		File file = null;
		file = new File(srcdir + "positive.txt");
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
		String dir = "/mnt/disk/liushenghua/sinawb201311to201402/";//读取文件
		String dir2 = "/mnt/disk/daeteam/zhd/data_processed_lcj/mess20160102/positive/";
		InputStream is = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		FileWriter fwZF = null;
		BufferedWriter bwZF = null;
		PrintWriter pwZF = null;
		BufferedReader reader = null;
		int p=11;int q=6;
	//	StringBuilder relationship=new StringBuilder();
		for (int i = p; i <=p; i++) {
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
						fw = new FileWriter(new File(dir2 + "positive_emoticon"+q
								+ ".csv"), true);
						bw = new BufferedWriter(fw);
						pw = new PrintWriter(bw);
						//*******************************
						fwZF = new FileWriter(new File(dir2 + "pfollowUserIdMsgId"+q 
								+ ".csv"), true);
						bwZF = new BufferedWriter(fwZF);
						pwZF = new PrintWriter(bwZF);
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
							
							String content=ss[5];
							for(String emoction:set){
								if(content.contains(emoction)){
									pw.write(ss[0]+"}|||}"+ss[1]+"}|||}"+ss[2]+"}|||}"+ss[3]+"}|||}"+ss[4]+"}|||}"+content
											+"}|||}"+ss[12]+"}|||}"+ss[13]+"}|||}"+ss[14]+"}|||}"+ss[15]+"\n");
									if(!"".equals(ss[4])){//用户找出用户的关系图
										pwZF.write(ss[0]+","+ss[1]+","+ss[2]+","+ss[3]+","+ss[4]+"\n");
									}
									break;
								}
							}
							//
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
							}}
							if (pwZF != null) {
								pwZF.close();
							}
							if (bwZF != null) {
								try {
									bwZF.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if (fwZF != null) {
								try {
									fwZF.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}}
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
						fw = new FileWriter(new File(dir2 + "positive_emoticon"+q
								+ ".csv"), true);
						bw = new BufferedWriter(fw);
						pw = new PrintWriter(bw);
						//*******************************************
						fwZF = new FileWriter(new File(dir2 + "pfollowUserIdMsgId"+q 
								+ ".csv"), true);
						bwZF = new BufferedWriter(fwZF);
						pwZF = new PrintWriter(bwZF);
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
							
							String content=ss[5];
							for(String emoction:set){
								if(content.contains(emoction)){
									pw.write(ss[0]+"}|||}"+ss[1]+"}|||}"+ss[2]+"}|||}"+ss[3]+"}|||}"+ss[4]+"}|||}"+content
											+"}|||}"+ss[12]+"}|||}"+ss[13]+"}|||}"+ss[14]+"}|||}"+ss[15]+"\n");
									if(!"".equals(ss[4])){
										pwZF.write(ss[0]+","+ss[1]+","+ss[2]+","+ss[3]+","+ss[4]+"\n");
									}
									break;
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
							}}
						
						if (pwZF != null) {
							pwZF.close();
						}
						if (bwZF != null) {
							try {
								bwZF.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (fwZF != null) {
							try {
								fwZF.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}}
						
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
