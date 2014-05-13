package test.d;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class Okwei {

//	POST /ajax/AjaxForm.aspx HTTP/1.1
//	Host: www.4004260.okwei.com
//	Connection: keep-alive
//	Content-Length: 76
//	Accept: text/html, */*; q=0.01
//	Origin: http://www.4004260.okwei.com
//	X-Requested-With: XMLHttpRequest
//	User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36
//	Content-Type: application/x-www-form-urlencoded
//	Referer: http://www.4004260.okwei.com/register.html
//	Accept-Encoding: gzip,deflate,sdch
//	Accept-Language: zh-CN,zh;q=0.8
//	Cookie: rdqq=4004260; IESESSION=alive; pgv_pvi=5609803776; pgv_si=s3525811200; history=4004260; Hm_lvt_ffa4dda2fc67ab38b1530f33200ae384=1392943030; Hm_lpvt_ffa4dda2fc67ab38b1530f33200ae384=1392943061
	
//	act=sdreg&jsrweino=4004260&weiname=%25u5475%25u5475&weipwd=fuck_okwei&vcode=
	
//	{"Status":1,"Message":"4084411"}
	
//	4084411

	public static final int D = 0x9fa5 - 0x4e00 + 1;
	
	public static final String Host = "www.4084411.okwei.com";
	public static final String Origin = "http://www.4084411.okwei.com";
	public static final String Referer = "http://www.4084411.okwei.com/register.html";
	public static final String URL = "http://www.4084411.okwei.com/ajax/AjaxForm.aspx";
	
	
	public static final String act = "sdreg";
	public static final String jsrweino = "4084411";
	public static final String weipwd = "fuck_okwei";

	public static void main(String[] args) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(URL);
		post.setHeader("Host", Host);
		post.setHeader("Origin", Origin);
		post.setHeader("Referer", Referer);
		
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
		post.setHeader("X-Requested-With", "XMLHttpRequest");
		
		char c = getRandomHan();
		String name = new String(new char[] {c, c});
		System.out.println(name);
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("act", act));  
        nvps.add(new BasicNameValuePair("jsrweino", jsrweino));  
        nvps.add(new BasicNameValuePair("weiname", name));  
        nvps.add(new BasicNameValuePair("weipwd", weipwd));  
        post.setEntity(new UrlEncodedFormEntity(nvps));
		
		HttpResponse response = client.execute(post);
		System.out.println(readResponse(response, "UTF-8"));
		
//		System.out.println(URLEncoder.encode("呵", "gb2312"));
	}
	
	public static char getRandomHan() {
		return (char) (0x4e00 + Math.random() * D);
	}
	
	private static String readResponse(HttpResponse response, String charset) throws Exception {
		InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), charset);
		char[] chars = new char[4096];
		StringBuilder sb = new StringBuilder();
		int length = -1;
		while ((length = reader.read(chars)) > 0)
			sb.append(chars, 0, length);
		return sb.toString();
	}

}
