package test.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Mysql_charset {

//	static final String constr = "jdbc:mysql://10.20.36.20:3308/idb_test?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true&max_allowed_packet=10485760";
//	static final String constr = "jdbc:mysql://10.20.149.16:3306/intl_wl?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8&connectTimeout=5000";
	static final String constr = "jdbc:mysql://10.20.149.16:3306/intl_wl?useUnicode=true&characterEncoding=utf8";
	static final String username = "idb_rnd";
	static final String password = "K6F8iV7BoFXV49D7B8Zz";
	
	 
//	static final String constr = "jdbc:mysql://localhost:3306/test";
//	static final String username = "test";
//	static final String password = "test";
	
	public static void main(String[] args) throws Exception {
//		String sql = "show full columns from a";
		String sql = "select name_cn from wl_china_area where id=0";
		Connection con = DriverManager.getConnection(constr, username, password);
		ResultSet rs = con.createStatement().executeQuery(sql);
		while(rs.next()) {
			
			String s = rs.getString(1);
			System.out.println(new String(s.getBytes("iso-8859-1"), "UTF-8"));
			
			InputStream in = rs.getBinaryStream(1);
			byte[] bs = new byte[2048];
			int length = in.read(bs);
			System.out.println(new String(bs,0,length, "GBK"));
			System.out.println(new String(bs,0,length, "GB2312"));
			System.out.println(new String(bs,0,length, "UTF-8"));
			System.out.println(rs.getString(1));
			
			System.out.println(rs.getNString(1));
			System.out.println(new String(rs.getBytes(1), "GBK"));
			System.out.println(new String(rs.getBytes(1), "gb2312"));
			System.out.println(new String(rs.getBytes(1), "iso-8859-1"));
			System.out.println(new String(rs.getBytes(1), "UTF-8"));
			System.out.println(new String(rs.getBytes(1), "Big5"));
			System.out.println(new String(rs.getBytes(1), "UTF-16"));
		}
		rs.close();
		con.close();
	}
}
