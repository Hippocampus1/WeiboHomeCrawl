package com.zhd.test;

import java.util.HashMap;

public class Test {
public static void main(String[] args) {
//String string="3661531059174098,1773490447,1773490447,阿乖michelle,,#SBS演技大赏#走起，@破月霓裳 @lyl2,ApOHDCuBk,360安全浏览器,,,,,0,0,0,1388460496000";
//String[] ss=string.split(",");
//System.out.println("".equals(ss[4]));
	// 构造hashmap
	HashMap newmap = new HashMap(); 

	// 给hashmap赋值
	newmap.put(1, "tutorials");
	newmap.put(2, "point");
	newmap.put(3, "is best");

	System.out.println("Values before remove: "+ newmap);

	// 移除key为2的value
	newmap.remove(2);

	System.out.println("Values after remove: "+ newmap.size());

}
}
