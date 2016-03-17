package com.zhd.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ReadFileList {

	/**
	 * 遍历文件夹下各个文件的全路径
	 * @param args
	 */
	private static ArrayList<String> al=new ArrayList<String>();
	public static boolean readfile(String filepath) throws FileNotFoundException, 
	IOException { 
	try { 
		
	File file = new File(filepath); 
	if (!file.isDirectory()) { 
	System.out.println("文件"); 
	System.out.println("path1=" + file.getPath()); 
	System.out.println("absolutepath1=" + file.getAbsolutePath()); 
	System.out.println("name1=" + file.getName()); 
 
	} 
	else if (file.isDirectory()) { 
	//System.out.println("文件夹"); 
	String[] filelist = file.list(); 
	for (int i = 0; i < filelist.length; i++) { 
	File readfile = new File(filepath + "\\" + filelist[i]); 
	if (!readfile.isDirectory()) { 
	String string=readfile.getPath();
	if((new File(string).length())>0)
	//System.out.println(string);
		{al.add(string);}
	} 
	else if (readfile.isDirectory()) { 
	readfile(filepath + "\\"+filelist[i]); 
	} 
	} 

	} 

	} 
	catch (FileNotFoundException e) { 
	System.out.println("readfile() Exception:" + e.getMessage()); 
	} 
	return true; 
	} 

	public static void main(String[] args) { 
	try { 
	readfile("D:\\lucene\\xingye_2013081"); 
	System.out.println(al.size());
	} 
	catch (FileNotFoundException ex) { 
	} 
	catch (IOException ex) { 
	} 
	System.out.println("ok"); 
	} 
public static ArrayList<String> filename()
{
	return al;
}
/**
 * 这部分是用来读rar，zip格式的文件
 */
private static List<String> alzip=new ArrayList<String>();

public static void zip(String zipFileName,String inputFile)throws Exception{

	zip(zipFileName,new File(inputFile));

}

public static void zip(String zipFileName,File inputFile)throws Exception{

	ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipFileName));

	zip(out,inputFile,"");

	System.out.println("zip done");

	out.close();

}



public void unzip(String zipFileName,String outputDirectory)throws Exception{

	ZipInputStream in=new ZipInputStream(new FileInputStream(zipFileName));

	ZipEntry z;

	while ((z=in.getNextEntry() )!= null)

	{

		System.out.println("unziping "+z.getName());

		if (z.isDirectory())

		{

			String name=z.getName();

			name=name.substring(0,name.length()-1);

			File f=new File(outputDirectory+File.separator+name);

			f.mkdir();

			System.out.println("mkdir "+outputDirectory+File.separator+name);

		}

		else{

			File f=new File(outputDirectory+File.separator+z.getName());

			f.createNewFile();

			FileOutputStream out=new FileOutputStream(f);

			int b;

			while ((b=in.read()) != -1)

				out.write(b);

			out.close();

		}

	}



	in.close();

}



public static void zip(ZipOutputStream out,File f,String base)throws Exception{

	//System.out.println("Zipping  "+f.getName());

	if (f.isDirectory())

	{

		File[] fl=f.listFiles();

		out.putNextEntry(new ZipEntry(base+"/"));

		base=base.length()==0?"":base+"/";

		for (int i=0;i<fl.length ;i++ )

		{

			zip(out, fl[i], base+fl[i].getName());
			//System.out.println(fl[i].getName());

		}

	}

	else

	{

		out.putNextEntry(new ZipEntry(base));

		FileInputStream in=new FileInputStream(f);

		int b;

		while ((b=in.read()) != -1)

			out.write(b);

		in.close();
		//System.out.println(f.getPath());
		alzip.add(f.getPath());

	}



}
public static List<String> showFiles()
{
	
	return alzip;
}

//public static void main(String[] args) 
//
//{
//
//	try{
//
//	ReadRar t=new ReadRar();
//
//	t.zip("D:\\lucene\\xingye_201308.rar","D:\\lucene\\xingye_201308");
//
//	//t.unzip("d:\\test.zip","d:\\test2");
//	List<String> list=t.showFiles();
//	for(String alzip:list)
//	{
//		System.out.println(alzip);
//	}
//	System.out.println(list.size());
//
//	}
//
//	catch(Exception e){e.printStackTrace(System.out);}
//
//}
}


