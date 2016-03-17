package com.zhd.test;

import java.util.Arrays;
import java.util.Comparator;

class SubGraph{
	int root_id;
	long num_of_nodes;
}
public class TestStructSort {
	public static void main(String[] args) {
		SubGraph[] subGraphs=new SubGraph[20];
		int[] root_of_parents=new int[20];
		long[] subgraph_nodes_num=new long[20];
		for (int i = 0; i < 20; i++) 
		{
			subGraphs[i]=new SubGraph();
			subGraphs[i].root_id=(int)(Math.random()*100);
			subGraphs[i].num_of_nodes=(long)(Math.random()*1000); //初始化subgraph[N]
			
		}
		for(int i=0;i<20;i++){
			System.out.println(subGraphs[i].num_of_nodes+"==>"+subGraphs[i].root_id);
		}
		Arrays.sort(subGraphs, 0, 20,new numComparator());
		for(int i=0;i<20;i++){
			System.err.println(subGraphs[i].num_of_nodes+"==>"+subGraphs[i].root_id);
		}
	}
	//Comparator是一个接口，所以这里我们自己定义的类numComparator要implents该接口
	static class numComparator implements Comparator { 
	    public int compare(Object object1, Object object2) {// 实现接口中的方法  
	        SubGraph p1 = (SubGraph) object1; // 强制转换  
	        SubGraph p2 = (SubGraph) object2;  
	        return new Long(p2.num_of_nodes).compareTo(new Long(p1.num_of_nodes));  
	    }  
	}  
}
