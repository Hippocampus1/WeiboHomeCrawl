package com.zhd.test;

import java.util.Vector;

public class TestVector {
public static void main(String[] args) {
	int[] a={1,2,3,4,5,6};
	int[] b={7,8,9,10};
	@SuppressWarnings("unchecked")
	Vector<Integer> v[]=new Vector[5];
	for(int i=0;i<5;i++){
	v[i]=new Vector<Integer>();
	}
	for(int i=0;i<5;i++){
	System.err.println(v[i].size());
	}
	for(int i=0;i<a.length;i++){
		v[0].add(a[i]);
	}
	for(int i=0;i<b.length;i++){
		v[1].add(b[i]);
	}
	//遍历
	//遍历Vector中的元素 
//for(int i = 0;i < v[0].size();i++){ 
//System.out.println(v[0].get(i)); 
//} 
//
//for(int i = 0;i < v[1].size();i++){ 
//System.err.println(v[1].get(i)); 
//} 
for(int i=0;i<5;i++){
	System.out.println(v[i].size());
	}
}
}
