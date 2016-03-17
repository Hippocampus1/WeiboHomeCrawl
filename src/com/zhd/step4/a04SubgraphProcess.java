package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;
/**
 * 
	* @ClassName: a04SubgraphProcess 
	* @Description:  根据MegerPair中获取的用户关系对，生成用户对编号：user_id_pair.txt
	* @author zeze
	* @date 2016年3月9日 下午4:02:14 
	*
 */
public class a04SubgraphProcess {
	

public static void main(String[] args) {
	String srcFileName="I:\\毕业设计\\数据集\\pair.csv";//args[0];//relationship
	String destFileName1="I:\\毕业设计\\数据集\\user_id_pair.txt";//args[1];//user_id_pair.txt
	String destFileName2="I:\\毕业设计\\数据集\\user_name_id.txt";//args[2];//user_name_id.txt
	String destFileName3="I:\\毕业设计\\数据集\\usercount1.txt";//args[3];//usercount.txt
	BufferedReader br=null;
	FileReader reader=null;
	Map<String, Long> maps = new LinkedHashMap<String, Long>();
	StringBuilder sBuilderIdPair = new StringBuilder();
	StringBuilder sBuilderIdName = new StringBuilder();
	long total=0;
	try {		
		reader = new FileReader(new File(srcFileName));
				br = FileReadUtil.getReadStream(reader);
				//************************************
				String s=null;
				long cnt = 0;
				while ((s = br.readLine()) != null) {
					String[] temp = s.split("\\|\\|");
					long x, y;
					if (!maps.containsKey(temp[0])) {
						maps.put(temp[0], ++cnt);
						x = cnt;
					} else {
						x = maps.get(temp[0]);
					}
					if (!maps.containsKey(temp[1])) {
						maps.put(temp[1], ++cnt);
						y = cnt;
					} else {
						y = maps.get(temp[1]);
					}
					// System.out.println(x+"==>"+y);
					sBuilderIdPair.append(x + "||" + y + "\n");
				}
				// 遍历maps
				for (Map.Entry<String, Long> entry : maps.entrySet()) {
					// System.out.println("key= " + entry.getKey() + " and value= "
					// + entry.getValue());
					sBuilderIdName.append(entry.getKey() + "||" + entry.getValue() + "\n");
				}
				total = maps.size();
				maps.clear();
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
	FileWriteUtil.WriteDocument(destFileName1,sBuilderIdPair.toString() );
	FileWriteUtil.WriteDocument(destFileName2,sBuilderIdName.toString() );
	FileWriteUtil.WriteDocument(destFileName3,String.valueOf(total));
}
}
