package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql_unique {

	static final String constr = "jdbc:mysql://localhost:3306/test";
	static final String username = "test";
	static final String password = "test";

	static final String sql = "insert into uni(code, time) values(?, ?)";
	static final String get_sql = "select id from uni where code = ? and time = ? limit 1";
	
	static final int size = 10000;
	
	public static void main(String[] args) throws SQLException  {
		Connection con = null;
		try {
			con = DriverManager.getConnection(constr, username, password);
//			con.setAutoCommit(false);
//			for (int i = 10000; i < 100000; i+=10000) {
//				PreparedStatement ps = con.prepareStatement(sql);
//				for (int j = i; j < i + 10000; j++) {
//					ps.setString(1, j + "");
//					ps.setInt(2, j);
//					ps.addBatch();
//				}
//				ps.executeBatch();
//				con.commit();
//			}
			
			long l = System.currentTimeMillis();
			for (int i = 110000; i < 110000 + size; i++) 
				tryInsert(con, i);
			
			System.out.println(System.currentTimeMillis() - l);
			l = System.currentTimeMillis();
			for (int i = 120000; i < 120000 + size; i++) 
				getInsert(con, i);
			System.out.println(System.currentTimeMillis() - l);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage().startsWith("Duplicate entry"));
		} finally {
			if (con != null && !con.isClosed())
				con.close();
		}
	}
	
	static void tryInsert(Connection con, int i) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, i + "");
			ps.setInt(2, i);
			ps.executeUpdate();
//			System.out.println("tryInsert");
		} catch (SQLException e) {
			if (!e.getMessage().startsWith("Duplicate entry"))
				e.printStackTrace();
		} finally {
			if (ps != null && !ps.isClosed())
				ps.close();
		}
	}
	
	static void getInsert(Connection con, int i) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(get_sql);
			ps.setString(1, i + "");
			ps.setInt(2, i);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
//				System.out.println("getInsert");
				ps = con.prepareStatement(sql);
				ps.setString(1, i + "");
				ps.setInt(2, i);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null && !ps.isClosed())
				ps.close();
		}
	}
}
