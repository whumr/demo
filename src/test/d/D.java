package test.d;

import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.util.NodeList;

public class D {

	static String url = "http://www.douban.com/group/topic/49043142/?author=1";
	static int count = 0;
	
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new Task(), 0, 5 * 60 * 1000);
	}
	
	public static String readResponse(HttpResponse response, String charset) throws Exception {
		InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), charset);
		char[] chars = new char[4096];
		StringBuilder sb = new StringBuilder();
		int length = -1;
		while ((length = reader.read(chars)) > 0)
			sb.append(chars, 0, length);
		return sb.toString();
	}

	static class Task extends TimerTask {
		@Override
		public void run() {
			HttpClient client = HttpUtil.getHttpClient();
			HttpGet get = new HttpGet(url);
			try {
				HttpResponse response = client.execute(get);
				String s = readResponse(response, "UTF-8");
				Parser p = new Parser();
				p.setEncoding("UTF-8");
				p.setInputHTML(s);
				NodeList list = p.extractAllNodesThatMatch(new TagNameFilter("P"));
				int to_list = list.size() - count;
				if (to_list > 0) {
					for (int i = count; i < list.size(); i++) {
						ParagraphTag pt = (ParagraphTag)list.elementAt(i);
						System.out.println(pt.getStringText().replaceAll("<br/>", "\n"));
						System.out.println();
					}
				}
				count = list.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
