package test.d;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

public class Analyzer {

	public static void main(String[] args) throws Exception {
//		args = "和田玉白玉资产经营2期5组---ZT201400055".split("---");
//		for (int i = 0; i < args.length; i++) {
//			System.out.println(args[i]);
//		}
//		String s = "http://www.352.com/trading3/webTradingRzbDetail.do?id=1182";
//		System.out.println(s.substring(s.lastIndexOf("=") + 1));
//		System.out.println(Spider.sdf.format(new Date(System.currentTimeMillis())));
//		System.out.println(Integer.parseInt("122.00".split("\\.")[0]));
//		s = "<span class=\"style1\">1000.00</span>";
//		System.out.println(s.substring(s.indexOf(">") + 1, s.indexOf("<", 1)));
//		s = "180 天";
//		System.out.println(s.substring(0, s.indexOf("天")));


		Spider.getData(19, 20);
		
//		for (int i = 1; i < 20; i += 2) {
//			final int index = i;
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						Spider.getData(index, index + 1);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}).start();
//		}
		
	}
	
}


class Spider {
	static HttpClient httpClient = HttpClientBuilder.create().build();
	static String charset = "UTF-8";
	static String url = "http://www.352.com/trading3/webTradingRzbList.do?pageSize=50&cursor=";
	static String NOT_FULL = "未满额", NOT_CHECKED = "未结算", NO_DATE = "--";
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static void getData(int start, int end) throws Exception {
		for (; start <= end; start++) {
			String s= get(url + start);
			System.out.println("get page " + start);
			List<Rzb> list = parse(s);
			System.out.println("parse page " + start);
			DbUtil.insert(list);
			System.out.println("insert page " + start);
		}
	}
	
	static String get(String url) throws Exception {
		HttpGet get = new HttpGet(url);
		get.setHeader("Host", "www.352.com");
		get.setHeader("Connection", "keep-alive");
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
		HttpResponse response = httpClient.execute(get);
		Header[] locationHeader = response.getHeaders("location");
		if (locationHeader != null && locationHeader.length > 0) {
			System.out.println(locationHeader[0].getValue());
			throw new Exception("xxx");
		} else
			return readResponse(response);
	}
	
	static String readResponse(HttpResponse response) throws Exception {
		InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), charset);
		char[] chars = new char[4096];
		StringBuilder sb = new StringBuilder();
		int length = -1;
		while ((length = reader.read(chars)) > 0)
			sb.append(chars, 0, length);
		return sb.toString();
	}
	
	static List<Rzb> parse(String data) throws Exception {
		List<Rzb> l = new ArrayList<Rzb>();
		Parser p = new Parser();
		p.setEncoding("UTF-8");
		p.setInputHTML(data);
		NodeList list = p.extractAllNodesThatMatch(new CssSelectorNodeFilter("div[class='rzbcollect_list']"));
		for (int i = 0; i < list.size(); i++) {
			Node node = list.elementAt(i);
			Rzb rzb = new Rzb();
			for (int j = 0; j < node.getChildren().size(); j++) {
				Node child = node.getChildren().elementAt(j);
				if (child instanceof Div) {
					for (int k = 0; k < child.getChildren().size(); k++) {
						Node c_node = child.getChildren().elementAt(k);
						//name---business_id
						if (c_node instanceof Span) {
							String[] s = ((Span)c_node).getStringText().split("---");
							rzb.name = s[0];
							rzb.business_no = s[1];
						//url,包含id
						} else if (c_node instanceof LinkTag) {
							String s = ((LinkTag)c_node).getLink();
							rzb.url = s;
							rzb.id = Integer.parseInt(s.substring(s.lastIndexOf("=") + 1));
						}
					}
				} else if (child instanceof TableTag) {
					TableTag table = (TableTag)child;
					//发布时间
					String s = table.getRow(0).getColumns()[3].getStringText().trim();
					if (!NO_DATE.equals(s))
						rzb.publish_date = new Date(sdf.parse(s).getTime());
					//满额时间
					s = table.getRow(3).getColumns()[3].getStringText().trim();
					if (!NO_DATE.equals(s))
						rzb.full_date = new Date(sdf.parse(s).getTime());
					//结算时间
					s = table.getRow(4).getColumns()[3].getStringText().trim();
					if (!NO_DATE.equals(s))
						rzb.check_date = new Date(sdf.parse(s).getTime());
					//项目管理方
					rzb.company = table.getRow(1).getColumns()[1].getStringText().split(" ")[0].trim();
					//合作金额
					s = table.getRow(2).getColumns()[1].getStringText().trim();
					rzb.money = Integer.parseInt(s.substring(s.indexOf(">") + 1, s.indexOf("<", 1)).split("\\.")[0]);
					//合作期限
					s = table.getRow(2).getColumns()[3].getStringText().trim();
					rzb.days = Integer.parseInt(s.substring(0, s.indexOf("天")).trim());
					//是否满额
					s = table.getRow(3).getColumns()[1].getStringText().trim();
					rzb.is_full = s.contains(NOT_FULL) ? "N" : "Y";
					//是否结算
					s = table.getRow(4).getColumns()[1].getStringText().trim();
					rzb.is_checked = s.contains(NOT_CHECKED) ? "N" : "Y";
				}
			}
			l.add(rzb);
		}
		return l;
	}
}

class DbUtil {
	static final String constr = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8", 
			username = "test", password = "test";
	static String sql = "insert into rzb (id, days, money, name, business_no, company, is_full, " +
			"is_checked, url, publish_date, full_date, check_date) values (?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static void insert(List<Rzb> list) throws SQLException {
		Connection con = DriverManager.getConnection(constr, username, password);
		PreparedStatement ps = con.prepareStatement(sql);
		try {
			for (Rzb rzb : list) {
				ps.setInt(1, rzb.id);
				ps.setInt(2, rzb.days);
				ps.setInt(3, rzb.money);
				ps.setString(4, rzb.name);
				ps.setString(5, rzb.business_no);
				ps.setString(6, rzb.company);
				ps.setString(7, rzb.is_full);
				ps.setString(8, rzb.is_checked);
				ps.setString(9, rzb.url);
				ps.setDate(10, rzb.publish_date);
				ps.setDate(11, rzb.full_date);
				ps.setDate(12, rzb.check_date);
				ps.addBatch();
			}
			ps.executeBatch();
		} finally {
			ps.close();
			con.close();
		}
	}
}

class Rzb {
	int id, days, money;
	String name, business_no, company, is_full, is_checked, url;
	Date publish_date, full_date, check_date;
	
//	create table rzb (id int, days int, money int, publish_date date, full_date date, check_date date,
//			name varchar(100), business_no varchar(100), company varchar(100), is_full char(1), is_checked char(1), url varchar(100));
}