package test.d;

import java.io.InputStreamReader;
import java.net.SocketTimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpUtil {

	private static HttpUtil httpUtil;
	
	private HttpUtil() {
	}
	
	public static HttpUtil getInstance() {
		if (httpUtil == null)
			httpUtil = new HttpUtil();
		return httpUtil;
	}
	
	public static HttpClient getHttpClient() {
		return HttpClientBuilder.create().build();
	}
	
	public String sendPost(HttpClient client, String s, String url, String host, String charset) throws Exception {
		return sendPost(client, s, true, url, host, charset);
	}
	
	public String sendPost(HttpClient client, String s, boolean wait, String url, String host, String charset) throws Exception {
		HttpPost post = getPost(url, host, charset);
		post.setEntity(new StringEntity(s, "UTF-8"));
		if (wait) {
			HttpResponse response = client.execute(post);
			String reply = readResponse(response, charset);
			post.completed();
			return reply;
		} else {
			Builder builder = RequestConfig.custom();
			builder.setSocketTimeout(1);
			post.setConfig(builder.build());
			try {
				client.execute(post);
			} catch (SocketTimeoutException e) {
			}
			post.completed();
			return null;
		}
	}
	
	private HttpPost getPost(String url, String host, String charset) {
		HttpPost post = new HttpPost(url); 
		post.setHeader("Host", host);
		post.setHeader("Content-Type", "text/xml; charset=" + charset);
		return post;
	}
	
	private String readResponse(HttpResponse response, String charset) throws Exception {
		InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), charset);
		char[] chars = new char[4096];
		StringBuilder sb = new StringBuilder();
		int length = -1;
		while ((length = reader.read(chars)) > 0)
			sb.append(chars, 0, length);
		return sb.toString();
	}
	
}