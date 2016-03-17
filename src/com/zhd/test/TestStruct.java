package com.zhd.test;
class tr{
		 char w;
		 char opr1;
		 char opr2;
		 int temp; 
		}
public class TestStruct {
	
		public static void main(String[] args) {
			tr[] trip=new tr[4];
			for (int i=0; i<trip.length; i++)
		        trip[i] = new tr();
			trip[0].w='1';
			System.err.println(trip[0].w);
			
		}
		
}
