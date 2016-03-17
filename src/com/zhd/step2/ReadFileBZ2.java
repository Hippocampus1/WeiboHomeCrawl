package com.zhd.step2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.commons.compress.compressors.bzip2.*;

public class ReadFileBZ2 {
	public static void main(String[] args) {
		String dir = "/mnt/disk/liushenghua/sinawb201311to201402/";
		String dir2 = "/mnt/disk/daeteam/zhd/data_processed_lcj/messages/";
		InputStream is = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		BufferedReader reader = null;
		int count = 4;
		for (int i = 7; i <= 12; i++) {
			String fileName1 = dir + "bz2_d" + i + "/";
			System.out.println(fileName1);

			File file1 = new File(fileName1);
			File[] files1 = file1.listFiles();
			for (File f1 : files1) {
				if (f1.getName().endsWith(".bz2")) {
					 System.out.println(f1.getPath());
					// 读取
					try {
						fw = new FileWriter(new File(dir2 + "message" + count
								+ ".csv"), true);
						bw = new BufferedWriter(fw);
						pw = new PrintWriter(bw);
						is = new BZip2CompressorInputStream(
								new FileInputStream(new File(f1.getPath())));
						reader = new BufferedReader(new InputStreamReader(is));
						String s = null;
					 System.out.println(	reader.readLine());
						while ((s = reader.readLine()) != null) {
							// System.out.println(s);
							pw.write(s + "\n");
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
			String fileName2 = dir + "bz2_d" + i + "/";
			// System.out.println(fileName2);
			File file2 = new File(fileName2);
			File[] files2 = file2.listFiles();
			for (File f2 : files2) {
				if (f2.getName().endsWith(".bz2")) {
					 System.out.println(f2.getPath());
					// 读取
					try {
						fw = new FileWriter(new File(dir2 + "message" + count
								+ ".csv"), true);
						bw = new BufferedWriter(fw);
						pw = new PrintWriter(bw);
						is = new BZip2CompressorInputStream(
								new FileInputStream(new File(f2.getPath())));
						reader = new BufferedReader(new InputStreamReader(is));
						System.out.println(	reader.readLine());
						String s = null;
						while ((s = reader.readLine()) != null) {
							// System.out.println(s);
							pw.write(s + "\n");
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
			count++;
		}
	}
}
