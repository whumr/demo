package test.stock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Parser {

	static String s = "_ntes_quote_callback({\"0603766\":{\"code\": \"0603766\", \"percent\": 0.001088, " +
			"\"high\": 9.3, \"askvol3\": 1355, \"askvol2\": 4825, \"askvol5\": 8700, \"askvol4\": 7025, " +
			"\"price\": 9.2, \"open\": 9.19, \"bid5\": 9.14, \"bid4\": 9.15, \"bid3\": 9.16, \"bid2\": 9.17, " +
			"\"bid1\": 9.18, \"low\": 9.15, \"updown\": 0.01, \"type\": \"SH\", \"bidvol1\": 18500, \"ask4\": 9.24, " +
			"\"bidvol3\": 16504, \"bidvol2\": 53033, \"symbol\": \"603766\", \"update\": \"2014/04/24 13:40:02\", " +
			"\"bidvol5\": 4000, \"bidvol4\": 29900, \"volume\": 2790213, \"askvol1\": 16621, \"ask5\": 9.25, " +
			"\"yestclose\": 9.19, \"ask1\": 9.2, \"name\": \"\u9686\u946b\u901a\u7528\", \"ask3\": 9.23, " +
			"\"ask2\": 9.21, \"arrow\": \"\u2191\", \"time\": \"2014/04/24 13:39:56\", \"turnover\": 25719627} });";
	
	public static void main(String[] args) {
		
//		double i;
//		Double x = null;
//		System.out.println(x);
//		i = x;
//		System.out.println(i);
		
		System.out.println(s.indexOf("{") + "\t" + s.lastIndexOf("}"));
		System.out.println(s.substring(s.indexOf("{"), s.lastIndexOf("}")));
		JSONObject json = JSON.parseObject(s.substring(s.indexOf("{"), s.lastIndexOf("}") + 1));
		
		System.out.println(json.keySet().size());
		
		System.out.println(json.getJSONObject("0603766").getString("name"));
		
		printJson(json.getJSONObject("0603766"));
		
	}
	
	static void printJson(JSONObject json) {
		for (String key : json.keySet()) {
			System.out.println(key + "\t=\t" + json.get(key));
		}
	}

}
