package com.zeze.test;

import java.io.File;

import com.zhd.util.FileWriteUtil;
/**
 * 
	* @ClassName: GetfileName 
	* @Description:打印文件目录下所有文件  
	* @author zeze
	* @date 2016年3月7日 下午2:36:10 
	*
 */
public class GetfileName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "I:\\毕业设计\\sinaweb";
		int cnt=0;
		File file = new File(path);
		File[] tempList = file.listFiles();
		StringBuffer sb = new StringBuffer();
		System.out.println("该目录下对象个数：" + tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				//System.out.println("文     件：" + tempList[i].getName());
				sb.append("\""+tempList[i].getName() + "\","+"\r\n");
				cnt++;
			}
			if (tempList[i].isDirectory()) {
				//System.out.println("文件夹：" + tempList[i].getName());
			}
		}
		System.out.println(sb.toString());
		System.err.println("写入数据........");
		String destfileName = "I:\\毕业设计\\数据集\\数据集文件名.txt";
		FileWriteUtil.WriteDocument(destfileName, sb.toString());
		System.err.println("写入成功！");
		System.out.println("文件数："+cnt);

	}

}
