package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Mysql_cobar {

//	static final String constr = "jdbc:mysql://10.20.149.14:3306/workoperations";
//	static final String username = "cluster_mgm";
//	static final String password = "cluster_mgm_pass";
	static final String constr = "jdbc:mysql://10.20.156.155:8066/xx";
	static final String username = "xx";
	static final String password = "xx";
	
	public static void main(String[] args) throws Exception {
		String sql = "show tables";
		Connection con = DriverManager.getConnection(constr, username, password);
		ResultSet rs = con.createStatement().executeQuery(sql);
		ResultSetMetaData md = rs.getMetaData();
		System.out.println(md.getColumnCount());
		for (int i = 1; i <= md.getColumnCount(); i++) {
			System.out.println(md.getColumnLabel(i) + "\t" + md.getColumnName(i));
		}
		System.out.println("--");
		while(rs.next()) {
			System.out.println(rs.getString(1)); 
//			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t|" + rs.getString(6) 
//					+ "|\t|" + rs.getString(9) + "|");
			System.out.println("---------------------");
		}
		rs.close();
		con.close();
	}
}
