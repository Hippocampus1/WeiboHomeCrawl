package com.zhd.step2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

public class TestReadFileBZ2 {
	public static void main(String[] args) {
		 File f = new File("C:\\Users\\zeze\\Desktop\\毕业设计\\sinaweb\\Month11_2_1.csv.bz2");
		  InputStream is = null;
		  try {
		   is = new BZip2CompressorInputStream(new FileInputStream(f));
		   BufferedReader reader = new BufferedReader(
		     new InputStreamReader(is));
		   String s = null;
		   while ((s = reader.readLine()) != null) {
		 //   System.out.println(s);
		    String[] ss=s.split(",");
		    if(!"".equals(ss[4])){
		    	System.out.println(s);
		    }
		    
		   }
		   reader.close();
		   is.close();
		  } catch (IOException e1) {
		   e1.printStackTrace();
		  }
	}
}
