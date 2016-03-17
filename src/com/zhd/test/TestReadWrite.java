package com.zhd.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.zhd.util.FileReadUtil;

public class TestReadWrite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br=null;
		FileReader reader=null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;//"J:\\user_id_pair.txt"
	
		String fileName="J:/test/maximum_subgraph_user_name_id.txt";
		String destFile="J:/test/maximum_subgraph_user_name_idcopy.txt";
		File file=new File(fileName);
		
		
		 try {
				reader = new FileReader(file);
				br = FileReadUtil.getReadStream(reader);
				fw = new FileWriter(new File(destFile), true);
				bw = new BufferedWriter(fw);
				pw = new PrintWriter(bw);
				String str = null;
				//strSubMess=new StringBuilder();
				while((str=br.readLine())!=null){
					
						pw.write(str+"\n");
					
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
