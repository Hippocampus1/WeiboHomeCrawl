package com.zhd.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;



public class PageContent {
	public static String getContent(String strUrl)
	// 一个public方法，返回字符串，错误则返回"error open url"
	{
		try {

			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "error open url" + strUrl;

		}
	}

	public static String getContentGBK(String strUrl)
	// 一个public方法，返回字符串，错误则返回"error open url"
	{
		try {

			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream(), "GBK"));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			return "error open url" + strUrl;

		}
	}

	public static String getContentGB2312(String strUrl)
	// 一个public方法，返回字符串，错误则返回"error open url"
	{
		try {

			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream(), "GB2312"));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			return "error open url" + strUrl;

		}
	}
	
}
