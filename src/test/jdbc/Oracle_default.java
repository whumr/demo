package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Oracle_default {

	static final String constr = "jdbc:oracle:thin:@10.20.149.18:1521:emdb";
	static final String username = "wardon";
	static final String password = "wardon";
	
	public static void main(String[] args) throws Exception {
		String sql = "select b,a from test";
		Connection con = DriverManager.getConnection(constr, username, password);
		ResultSet rs = con.createStatement().executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString(1) + "\t|" + rs.getString(2) + "|");
		}
		rs.close();
		con.close();
//		String sql = "select COLUMN_NAME, DATA_TYPE, Data_Default " +
//				"from user_tab_columns " +
//				"where TABLE_NAME = 'TEST'";
//		Connection con = DriverManager.getConnection(constr, username, password);
//		ResultSet rs = con.createStatement().executeQuery(sql);
//		while(rs.next()) {
//			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t|" + rs.getString(3) + "|");
//		}
//		rs.close();
//		con.close();
		
		
	}
}