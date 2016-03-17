package com.zhd.step;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;

//从belong.txt和user_id_pair.txt中读出数据，进行处理，输出最大子图所有边的集合 
//信息到maximum_subgraph_pair.txt文件中保存,处理的事有向图
public class Maximum_subgraph_pair05 {
	public final static int N = 20000001;
	public void maximum_subgraph_pair_process(){
		int[] nodes_id=new int[N];//存储所有点对应的子图id号
		for(int i=0;i<N;i++){
		   nodes_id[i]=0;
		   System.out.println(i);
		}
		BufferedReader br=null;
		FileReader reader=null;
		String dir = "/home/fzuir/ZhdExp/data_processed_lcj/";
		//String dir="J:/";
		File file = null;
		file = new File(dir + "belong.txt");
	    int cnt = 0;//表示点的id号
	    int u=0;//临时存储点的id号
	    try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				u = Integer.parseInt(str);
				nodes_id[++cnt] = u;////存储所有点对应的子图id号
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
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	    file = new File(dir + "subgraph.txt");//// 从subgraph.txt读出最大子图的id号
		int maxinum_subgraph_id = 0;
		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			str = br.readLine();
			maxinum_subgraph_id = Integer.parseInt(str.split("\\|\\|")[0]);
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
		 file = new File(dir + "user_id_pair.txt");//// 从subgraph.txt读出最大子图的id号
			int v1=0,v2=0;
			StringBuilder sBuilderMaxSubPair=new StringBuilder();
			try {
				reader = new FileReader(file);
				br = FileReadUtil.getReadStream(reader);
				String str = null;
				while((str = br.readLine()) != null){
					String[] ss=str.split("\\|\\|");
					v1=Integer.parseInt(ss[0]);
					v2=Integer.parseInt(ss[1]);
					if(nodes_id[v1]==maxinum_subgraph_id&&nodes_id[v2]==maxinum_subgraph_id){
						System.out.println(v1+"||"+v2);
						sBuilderMaxSubPair.append(v1+"||"+v2+"\n");
					}
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
				if (reader != null)
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			//写入
			String file2=dir+"maximum_subgraph_pair.txt";
			FileWriteUtil.WriteDocument(file2, sBuilderMaxSubPair.toString());

	}
	public static void main(String[] args) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		System.out.println("开始运行");
		Date nowDate = new Date();
		new Maximum_subgraph_pair05().maximum_subgraph_pair_process();
		// System.out.println("已完成");
		Date endDate = new Date();
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		System.out.println(day + "天" + hour + "小时" + min + "分钟");
	}
}
