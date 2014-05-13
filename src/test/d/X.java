package test.d;

import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.alibaba.fastjson.JSON;

public class X {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		https://openapi.youku.com/v2/videos/by_user.json
//			client_id	string	true		应用Key	
//			user_id	int	false		 用户ID	
//			user_name	string	false		 用户名	
//			orderby	string	false	published	排序
//			published: 发布时间
//			view-count: 总播放数
//			comment-count: 总评论数
//			favorite-count: 总收藏数	published
//			page	int	false	1	页数	1
//			count	int	false	20	页大小	20
		
//		"total" : 100,
//	    "page" : 1,
//	    "count" : 20,
//	    "videos" :
//	    [
//	        {
//	            "id" : "XMjg1MTcyNDQ0",
//	            "title" : "泡芙小姐的灯泡 11",
//	            "link" : "http://v.youku.com/v_show/id_XMjg1MTcyNDQ0.html",
//	            "thumbnail" : "http://g4.ykimg.com/0100641F464E1FC...",
//	            "duration" : "910",
//	            "category" : "原创",
//	            "state" : "normal",
//	            "view_count" : 2555843,
//	            "favorite_count" : 534,
//	            "comment_count" : 1000,
//	            "up_count" : 14859,
//	            "down_count" : 559,
//	            "published" : "2011-07-15 09:00:42",
//	            "operation_limit": ["COMMENT_DISABLED"],
//	            "streamtypes" : ["flv","flvhd","hd"]
//	        }
//	        …
//	    ]
		
//		String url = "https://openapi.youku.com/v2/videos/by_user.json?client_id=f4068d8c2d21d6cd&count=10&user_name=";
//		
//		String list_url = "https://openapi.youku.com/v2/playlists/by_user.json?client_id=f4068d8c2d21d6cd&count=10&user_name=";
		
		String user_url = "https://openapi.youku.com/v2/users/show.json?client_id=f4068d8c2d21d6cd&user_name=";
//		String user_name = "老鼠sjq";
		String user_name = "邪恶的小调";
		
		HttpClient client = HttpUtil.getHttpClient();
//		HttpGet get = new HttpGet(url + user_name);
		HttpGet get = new HttpGet(user_url + user_name);
//		get.setHeader("Host", "www.352.cn");
//		get.setHeader("Connection", "keep-alive");
//		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
		
		HttpResponse response = client.execute(get);
		System.out.println(JSON.parseObject(readResponse(response, "UTF-8")).toJSONString());
//		
//		
//		JSONObject json = JSON.parseObject(readResponse(response, "UTF-8"));
//		
//		int total = json.getInteger("total");
//		System.out.println(total);
//		JSONArray vedios = json.getJSONArray("videos");
//		for (int i = 0; i < vedios.size(); i++) {
//			JSONObject vedio = vedios.getJSONObject(i);
//			StringBuilder sb = new StringBuilder();
//			sb.append(vedio.getString("title")).append("\t")
//				.append(vedio.getString("category")).append("\t")
//				.append(vedio.getString("published")).append("\t")
//				.append(vedio.getString("thumbnail")).append("\t")
//				.append(vedio.getString("streamtypes"));
//			System.out.println(sb.toString());
//		}
//		
//		System.out.println(vedios.getJSONObject(0).toJSONString());
//		
//		System.out.println(vedios.size());
//		
//		
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		
//		get = new HttpGet(list_url + user_name);
////		get.setHeader("Host", "www.352.cn");
////		get.setHeader("Connection", "keep-alive");
////		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
//		
//		response = client.execute(get);
//		
//		json = JSON.parseObject(readResponse(response, "UTF-8"));
//		
//		total = json.getInteger("total");
//		System.out.println(total);
//		JSONArray playlists = json.getJSONArray("playlists");
//		for (int i = 0; i < playlists.size(); i++) {
//			JSONObject playlist = playlists.getJSONObject(i);
//			StringBuilder sb = new StringBuilder();
//			sb.append(playlist.getString("name")).append("\t")
//				.append(playlist.getString("id")).append("\t")
//				.append(playlist.getString("published")).append("\t")
//				.append(playlist.getString("thumbnail")).append("\t")
//				.append(playlist.getString("user"));
//			System.out.println(sb.toString());
//		}
//		
//		System.out.println(playlists.getJSONObject(0).toJSONString());
//		
//		System.out.println(playlists.size());

//		System.out.println(readResponse(response, "UTF-8"));	
		
		
//		System.out.println("\u3010\u7687\u51a0\u7684\u6545\u4e8b\u3011\u9b54\u517d\u4e89\u9738xiaoy\u89e3\u8bf4");

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
}
