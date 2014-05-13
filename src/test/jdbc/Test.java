package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Test {

	static final String constr = "jdbc:mysql://10.20.36.20:3306/idb_test";
	static final String username = "idb";
	static final String password = "idb";
	
	static final String o_constr = "jdbc:oracle:thin:@10.20.149.18:1521:emdb";
	static final String o_username = "idb";
	static final String o_password = "idb";
	
	
	public static void main(String[] args) throws Exception {
		String sql = "select c.column_name,c.column_default, c.column_type,c.column_comment " +
				"from information_schema.columns c " +
				"where c.table_schema='idb_test' and c.table_name in ('a1') order by c.ordinal_position";
		Connection con = DriverManager.getConnection(constr, username, password);
		ResultSet rs = con.createStatement().executeQuery(sql);
		if(rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(4));
		}
		rs.close();
		con.close();
		
		sql = "select t.table_name,t.column_name,t.data_default,t.comments " +
				"from ddl_table_column_draft t where t.project_id=5281 and t.table_name='a1' " +
				"and column_name = 'id'";
		con = DriverManager.getConnection(o_constr, o_username, o_password);
		rs = con.createStatement().executeQuery(sql);
		if(rs.next()) {
			System.out.println(rs.getString(2) + "\t" + rs.getString(4));
		}
		rs.close();
		con.close();
		
	}

}
