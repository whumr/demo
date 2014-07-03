package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Mysql_default {

//	static final String constr = "jdbc:mysql://10.20.36.20:3308/idb_test?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true&max_allowed_packet=10485760";
	static final String constr = "jdbc:mysql://10.20.36.20:3306/dd";
	static final String username = "dd";
	static final String password = "dds";
//	static final String constr = "jdbc:mysql://localhost:3306/test";
//	static final String username = "test";
//	static final String password = "test";
	
	public static void main(String[] args) throws Exception {
//		String sql = "show full columns from a";
		String sql = "select c.column_name,c.column_default, c.column_type " +
				"from information_schema.columns c " +
				"where c.table_schema='idb_test' and c.table_name in ('a') order by c.ordinal_position";
		Connection con = DriverManager.getConnection(constr, username, password);
		ResultSet rs = con.createStatement().executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(3) + "\t|"
					+ (rs.getString(2) == null ? "#" : rs.getString(2)) + "|");
		}
		rs.close();
		con.close();
	}
}
