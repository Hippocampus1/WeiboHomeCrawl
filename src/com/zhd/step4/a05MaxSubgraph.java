package com.zhd.step4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.zhd.util.FileReadUtil;
import com.zhd.util.FileWriteUtil;
class SubGraph {
		int root_id;
		long num_of_nodes;
	}
public class a05MaxSubgraph {
	
	/**
	 * 分割出每个关联的子图
	 * 输入：user_id_pair.txt
	 * 输出：belong.txt: 每个用户对应的子图的id
	        subgraph.txt: subgraph_id, num_of_nodes_of_subgraph     
	 * @author Administrator
	 *
	 */
		public final static int N = 350000;

		public int findRoot(int x, int rp[]) {
			return rp[x] == x ? rp[x] : (rp[x] = findRoot(rp[x], rp));
		}

		// 定义排序规则，以子图所含点的数目从大大小排序
		public boolean compare(SubGraph s, SubGraph v) {
			return s.num_of_nodes > v.num_of_nodes;
		}

		@SuppressWarnings("unchecked")
		public void subgraphProcess(String srcFileName,String destfile2,String destfile3) {
			SubGraph[] subGraphs = new SubGraph[N];
			int[] root_of_parents = new int[N];
			long[] subgraph_nodes_num = new long[N];
			for (int i = 0; i < N; i++) {
				subGraphs[i] = new SubGraph();
				subGraphs[i].root_id = 0;
				subGraphs[i].num_of_nodes = 0; // 初始化subgraph[N]
				root_of_parents[i] = i; // 并查集初始化，其父节点为本身
				subgraph_nodes_num[i] = 0; // 初始化subgraph_nodes_num
				//System.out.println(i);
			}
			 String dir="F:/zhd/";
			//String dir = "J:/";
			BufferedReader br = null;
			FileReader reader = null;
			// File file = new File("J:\\pair.txt");
			File file = new File(srcFileName);
			int u, v;// u,v表示相连接的两点
			int num_of_nodes = 0;// 用来记录hash.txt总共包含的顶点数目，初始化为0
			StringBuilder sBuilderBelong = new StringBuilder();
			StringBuilder sBuilderSubgraph = new StringBuilder();
			try {
				reader = new FileReader(file);
				br = FileReadUtil.getReadStream(reader);
				String str = null;
				while ((str = br.readLine()) != null) {
					String[] ss = str.split("\\|\\|");
					u = Integer.parseInt(ss[0]);
					v = Integer.parseInt(ss[1]);
					int x = findRoot(u, root_of_parents);
					int y = findRoot(v, root_of_parents);
					if (x == y)
						continue;
					if (x < y)
						root_of_parents[y] = x;
					else
						root_of_parents[x] = y;
					num_of_nodes = Math.max(u, num_of_nodes);// <Algorithm>函数max
					num_of_nodes = Math.max(v, num_of_nodes);
				} // num_of_nodes记录所有点的总数目，建立好并查集，并且子图以最小的点表示父根节点，最终结果是f[i]记录的是父根节点
				System.out.println("总共节点数为："+num_of_nodes);
				int num_of_subgraph = 0;// 表示有多少个子图，初始化为0

				for (int i = 1; i <= num_of_nodes; i++) {
					int t = findRoot(i, root_of_parents);// 返回改点所属子图的父根节点（默认以该子图的最小结点）
					sBuilderBelong.append(t + "\n");
					// printf("%ld\n", t);//保存每个节点对应子图的根节点到belong.txt文件中

					if (i == t)
						subGraphs[num_of_subgraph++].root_id = t;
					// 存储子图信息，所有子图都用最小点的root_id表示，
					// 并用num_of_subgraph来记录是第几个子图,相等表示增加了一个新的子图

					subgraph_nodes_num[t]++;// 对应子图的所有点的数目增加一个
				}
				// 此时num_of_subgraph表示子图的总数目
				for (int i = 0; i < num_of_subgraph; i++)
					subGraphs[i].num_of_nodes = subgraph_nodes_num[subGraphs[i].root_id];
				// subgraph[i]表示第i个子图的信息，subgraph[i].root_id表示第i个子图的父根节点，
				// subgraph_nodes_num[subgraph[i].root_id]表示第i个子图所含的点数

				// Arrays.sort(subgraph, subgraph + num_of_subgraph,
				// compare);//按照所有子图所含的点数排序，从大到小排序
				Arrays.sort(subGraphs, 0, num_of_subgraph, new numComparator());
				for (int i = 0; i < num_of_subgraph; i++) {
					// System.out.println(subGraphs[i].root_id+"||"+subGraphs[i].num_of_nodes);
					sBuilderSubgraph.append(subGraphs[i].root_id + "||" + subGraphs[i].num_of_nodes + "\n");
				}
				// 保存到subgraph.txt文件中，格式为子图的根节点、该子图点的数目

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(br!=null)
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(reader!=null)
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		
			FileWriteUtil.WriteDocument(destfile2, sBuilderBelong.toString());
			FileWriteUtil.WriteDocument(destfile3, sBuilderSubgraph.toString());
		}

		// 从大到小排序
		static class numComparator implements Comparator {
			public int compare(Object object1, Object object2) {// 实现接口中的方法
				SubGraph p1 = (SubGraph) object1; // 强制转换
				SubGraph p2 = (SubGraph) object2;
				return new Long(p2.num_of_nodes).compareTo(new Long(p1.num_of_nodes));
			}
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			long nd = 1000 * 24 * 60 * 60;
			long nh = 1000 * 60 * 60;
			long nm = 1000 * 60;
			// long ns = 1000;
			// 获得两个时间的毫秒时间差异
			System.out.println("开始运行");
			String srcFileName="I:\\毕业设计\\数据集\\user_id_pair.txt";//args[0];//user_id_pair.txt
			String destfile2="I:\\毕业设计\\数据集\\belong.txt";//args[1];//belong.txt
			String destfile3="I:\\毕业设计\\数据集\\subfraph.txt";//args[2];//subgraph.txt
			Date nowDate = new Date();
			new a05MaxSubgraph().subgraphProcess(srcFileName, destfile2, destfile3);
			System.out.println("已完成");
			Date endDate = new Date();
			long diff = endDate.getTime() - nowDate.getTime();
			// 计算差多少天开始运行
			
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
