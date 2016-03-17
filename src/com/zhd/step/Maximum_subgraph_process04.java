package com.zhd.step;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;


public class Maximum_subgraph_process04 {
	public final static int N = 20000001;

	public void maximum_subgraph_process() {
		// 存储每一个点所属于的子图id
		int nodes_subgraph[] = new int[N];
		for (int i = 0; i < N; i++) {
			nodes_subgraph[i] = 0;
			System.out.println(i);
		}
		BufferedReader br = null;
		FileReader reader = null;
		// File file = new File("J:\\pair.txt");
		String dir = "/home/fzuir/ZhdExp/data_processed_lcj/";
		//String dir="J:/";
		File file = null;
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

		file = new File(dir + "belong.txt");// belong.txt保存的是每一个点所属的子图id
		int cnt = 0, u = 0;// cnt表示记录的点数，u用来表示子图的id号
		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				u = Integer.parseInt(str);
				nodes_subgraph[++cnt] = u;
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

		file = new File(dir + "user_id_pair.txt");
		int v1 = 0, v2 = 0, m = 0;// m表示最大子图总的边数目
		Vector<Integer> g[] = new Vector[N];
		for (int i = 0; i < N; i++) {
			g[i] = new Vector<Integer>();
		}
		try {
			reader = new FileReader(file);
			br = FileReadUtil.getReadStream(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				String[] ss = str.split("\\|\\|");
				v1 = Integer.parseInt(ss[0]);
				v2 = Integer.parseInt(ss[1]);
				if (nodes_subgraph[v1] == maxinum_subgraph_id && nodes_subgraph[v2] == maxinum_subgraph_id) {// 这边算的是无向图
					g[v1].add(v2);// push_back操作会自动扩大vector的容量
					g[v2].add(v1);
					m++;// 每每增加一条边，m加1
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
		// 进行写入
		StringBuilder sBuilderMaxinum_subgraph = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int k = g[i].size();// 排除与其相连点数为0的点
			if (k != 0) {
				sBuilderMaxinum_subgraph.append(i + "|" + k);
				for (int j = 0; j < k; j++) {
					sBuilderMaxinum_subgraph.append("||" + g[i].get(j));
				}
				sBuilderMaxinum_subgraph.append("\n");
			}

		}
		StringBuilder sBuilderMaxinum_subgraph_id_nodes_edges = new StringBuilder();
		int n = 0;// 算最大子图的点数
		for (int i = 0; i < N; i++) {
			if (nodes_subgraph[i] == maxinum_subgraph_id) {
				++n;
			}
		}
		sBuilderMaxinum_subgraph_id_nodes_edges
				.append("最大子图的id号为:" + maxinum_subgraph_id + "||" + "点数为:" + n + "||" + "边数为：" + m);
		String file2=dir+"maximum_subgraph.txt";
		String file3=dir+"maximum_subgraph_id_nodes_edges.txt";
		FileWriteUtil.WriteDocument(file2, sBuilderMaxinum_subgraph.toString());
		FileWriteUtil.WriteDocument(file3,sBuilderMaxinum_subgraph_id_nodes_edges.toString());
	}
	public static void main(String[] args) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		System.out.println("开始运行");
		Date nowDate = new Date();
		new Maximum_subgraph_process04().maximum_subgraph_process();
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
