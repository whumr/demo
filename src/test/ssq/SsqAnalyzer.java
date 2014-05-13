package test.ssq;

import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;

import test.d.HttpUtil;

public class SsqAnalyzer {
	
	static String url = "http://kaijiang.zhcw.com/zhcw/html/ssq/list#.html";
	
	static Pattern Red = Pattern.compile("<em class=\"rr\">(\\d+)</em>");
	static Pattern Blue = Pattern.compile("<em>(\\d+)</em>");

	public static void main(String[] args) throws Exception {
		HttpClient client = HttpUtil.getHttpClient();
		Parser p = new Parser();
		p.setEncoding("UTF-8");
		List<Ssq> l = new ArrayList<Ssq>();
		
		for (int i = 1; ; i++) {
			HttpGet get = new HttpGet(i > 1 ? url.replaceFirst("#", "_" + i) : url.replaceFirst("#", ""));
			get.setHeader("Host", "kaijiang.zhcw.com");
			get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
			HttpResponse response = client.execute(get);
			
			String s = readResponse(response);
			
			p.setInputHTML(s);
			
			NodeList list = p.extractAllNodesThatMatch(new CssSelectorNodeFilter("td[style='padding-left:10px;']"));
			if (list.size() <= 0)
				break;
			for (int j = 0; j < list.size(); j++) {
				Ssq ssq = new Ssq();
				int n = 0;
				String html = list.elementAt(j).toHtml();
				Matcher m = Red.matcher(html);
				while (m.find())
					ssq.reds[n++] = Integer.parseInt(m.group(1).trim());
				m = Blue.matcher(html);
				while (m.find()) 
					ssq.blue = Integer.parseInt(m.group(1).trim());
				l.add(ssq);
			}
		}
		
		System.out.println(l.size());
		FileWriter w = new FileWriter("ssq.txt");
		for (Ssq ssq : l) {
			w.write(ssq.toString());
			w.write("\n");
		}
		w.flush();
		w.close();
	}

	public static String readResponse(HttpResponse response) throws Exception {
		InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
		char[] chars = new char[4096];
		StringBuilder sb = new StringBuilder();
		int length = -1;
		while ((length = reader.read(chars)) > 0)
			sb.append(chars, 0, length);
		return sb.toString();
	}
}

class Ssq {
	int[] reds = new int[6];
	int blue;
	
	public int compute(Ssq that) {
		if (equals(that))
			return 1;
		Set<Integer> set = new HashSet<Integer>();
		for(int red : reds)
			set.add(red);
		for(int red : that.reds)
			set.add(red);
		int same_red = 12 - set.size();
		boolean same_blue = blue == that.blue;
		switch (same_red) {
			case 6 : return 2;
			case 5 : return same_blue ? 3 : 4;
			case 4 : return same_blue ? 4 : 5;
			case 3 : if (same_blue) return 5;
			default : if (same_blue) return 6;
		}
		return 10;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ssq))
			return false;
		Ssq that = (Ssq)obj;
		for (int i = 0; i < reds.length; i++) {
			if (reds[i] != that.reds[i])
				return false;
		}
		return blue == that.blue;
	}
	
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < reds.length; i++) {
			buffer.append(reds[i]).append("\t");
		}
		buffer.append(blue);
		return buffer.toString();
	}
}
