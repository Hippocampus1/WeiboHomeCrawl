package com.zhd.test;

import java.util.Arrays;
/**
 * 
	* @ClassName: TestSort 
	* @Description: 数组排序测试  
	* @author zeze
	* @date 2016年3月2日 下午2:59:01 
	*
 */
public class TestSort {
	public static void main(String[] args) {
		int[] subGraphs = { 11, 22, 33, 66, 55, 44 };
		Arrays.sort(subGraphs);//Arrays.sort(subGraphs, 0, subGraphs.length - 1);
		for (int i = 0; i < subGraphs.length; i++) {
			System.out.println(subGraphs[i]);
		}
	}

}
