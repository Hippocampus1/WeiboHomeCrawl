package com.zeze.test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 更新
 * 
 * @ClassName: WeiboHomeCrawl
 * @Description: httpclient结合Jsoup解析，采集微博主页，获取博主粉丝数和微博数，格式：用户名||关注||粉丝||发布微博数
 * @author zeze
 * @date 2016年3月16日 下午4:00:34
 *
 */

public class WeiboHomeCrawl {

	private static int cnt = 0;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String messDir = "I:\\毕业设计\\数据集\\";
	private static String destFileName = messDir + "WeiboHomeCrawl\\user_fans.txt";
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	private static PrintWriter pw = null;//
	private Logger loger;// Logger实例
	private static WeiboHomeCrawl log;

	// 构造函数，用于初始化Logger配置需要的属性
	private WeiboHomeCrawl() {
		// 获得当前目录路径
		String filePath = this.getClass().getResource("/").getPath();
		filePath = filePath.substring(1).replace("bin", "src");
		loger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure(filePath + "log4j.properties");// loger所需的配置文件路径
	}

	static WeiboHomeCrawl getLoger() {
		if (log != null)
			return log;
		else
			return new WeiboHomeCrawl();
	}

	public void crawl(String dataUrl) {
		WeiboHomeCrawl log = WeiboHomeCrawl.getLoger();
		HttpClient httpClient = new HttpClient();
		int pos = 0;
		int end = 0;

		try {
			// 设置 Cookie
			String cookies = "SUB=_2A2577u6FDeTxGeNG7FER9SnNyzWIHXVZEPLNrDV6PUJbstBeLUvDkW1LHesLkMh4jhqWQbhOfsw6kn5bzp782Q..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWIfik8gDvAW4HdRkl3mvu85JpX5o2p; SUHB=0xnSutO1lmL-0r; SSOLoginState=1458216661; gsid_CTandWM=4uTdCpOz5d6Wp69s80HEhoDQJ1L; _T_WM=0c27da3afe1dcfb6be7ebd05a6d3ebd2";
			GetMethod getMethod = new GetMethod(dataUrl);
			// 每次访问需授权的网址时需带上前面的 cookie 作为通行证
			getMethod.setRequestHeader("cookie", cookies);
			// getMethod.setRequestHeader("Referer",
			// "http://passport.mop.com/");
			getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36 QIHU 360SE");
			int statusCodes = httpClient.executeMethod(getMethod);// 获取状态码
			// System.out.println("状态码："+statusCodes);

			InputStream inputStream = getMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			// Element result=text;
			Document doc = Jsoup.parse(stringBuffer.toString());
			Elements name = doc.select("title");
			Elements part = doc.select("[class= tip2]").select("a");
			// System.out.println(part);
			String user = name.text();
			String post = doc.select("[class=tip2]").select("[class=tc]").text();
			String follow = null;
			try {
				follow = part.get(0).text();
			}
			catch (Exception e) {
				// TODO: handle exception
				new WeiboHomeCrawl().crawl(str);
				Thread.sleep(10000);// 暂停1s
				System.out.println(e.getMessage());
				log.loger.error("url:"+dataUrl+" "+e.getMessage());// 写入到日志文件
			}

			String fans = part.get(1).text();

			pos = follow.indexOf("[") + 1;// 关注
			end = follow.indexOf("]");
			follow = follow.substring(pos, end);

			pos = fans.indexOf("[") + 1;// 粉丝
			end = fans.indexOf("]");
			fans = fans.substring(pos, end);

			pos = post.indexOf("[") + 1;// 发布微博数
			end = post.indexOf("]");
			post = post.substring(pos, end);

			cnt++;
			System.out.println(cnt + ":" + name.text() + "||" + follow + "||" + fans + "||" + post);

			// 写入url_fans.txt
			try {
				fw = new FileWriter(new File(destFileName), true);
				bw = new BufferedWriter(fw);
				pw = new PrintWriter(bw);
				pw.write(name.text() + "||" + follow + "||" + fans + "||" + post + "\n");
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.loger.error("写入错误"+e.getMessage());
			}
			finally {
				if (pw != null) {
					pw.close();
				}
				if (bw != null) {
					try {
						bw.close();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fw != null) {
					try {
						fw.close();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String dir = "I:\\毕业设计\\数据集\\FilterPairUID\\";
		File file = new File(dir + "user_url.txt");
		FileReader reader = null;
		BufferedReader br = null;

		try {
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			String str = null;
			try {
				str = br.readLine();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (str != null) {
				new WeiboHomeCrawl().crawl(str);
				try {
					str = br.readLine();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(3000);// 暂停3s
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				}
				catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		System.out.println(cnt);

	}
}