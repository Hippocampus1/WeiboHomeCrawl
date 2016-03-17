package com.zhd.test;
/**
 * 
	* @ClassName: Mid2IdUtil 
	* @Description:  新浪id转url (id转mid)
	* @author zeze
	* @date 2016年3月2日 上午10:49:40 
	*
 */
public class Mid2IdUtil {
	public static void main(String[] args) {
		System.out.println(Id2Mid("3676608718380816"));
	}
	private static String[] str62keys = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
			"x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z" };

	public static String IntToEnode62(Integer int10) {
		String s62 = "";
		int r = 0;
		while (int10 != 0) {
			r = int10 % 62;
			s62 = str62keys[r] + s62;
			int10 = (int) Math.floor(int10 / 62.0);
		}
		return s62;
	}

	public static String Id2Mid(String str10) {
		String mid = "";
		int count = 1 ;
		for (int i = str10.length() - 7; i > -7; i = i - 7) // 从最后往前以7字节为一组读取字符
		{
			int offset = i < 0 ? 0 : i;
			int len = i < 0 ? str10.length() % 7 : 7;
			String temp = str10.substring(offset, offset + len);
			String url =IntToEnode62(Integer.valueOf(temp));
			if(count!=3){//z xghm uXym 生成的链接从右往左的前2组，4位一组，不足4位的补0
				for(int j=0;j<4-url.length();j++){
					url = "0"+url;
				}
			}
			mid = url+mid;                       
                      count++;
               }
		return mid;
	}
}