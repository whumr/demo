package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sqlite {

	public static void main(String[] args) throws Exception {
		Class.forName("org.sqlite.JDBC");  
        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");  
        Statement stat = conn.createStatement();  
        
//        stat.executeUpdate("create table words(girl_id int, time int, feel int, relation int, w_id int)");  
//        stat.executeUpdate("create table pics(girl_id int, time int, place int)");  
//        stat.executeUpdate("create table word_contents(id int, content varchar(1000), audio varchar(100))");  
        
//        stat.execute("create table t (id int, name varchar(20))");
        stat.execute("create index idx_name on t(name)");
        
        PreparedStatement prep = conn.prepareStatement(
        		"insert into people values (?, ?)");  

        prep.setString(1, "sf额问问");  
        prep.setString(2, "WWW");  
        prep.addBatch();  
        prep.setString(1, "we");  
        prep.setString(2, "人味儿");  
        prep.addBatch();  
        prep.setString(1, "温热");  
        prep.setString(2, "认为惹我");  
        prep.addBatch();  
        prep.executeBatch();  
        
        
        ResultSet rs = stat.executeQuery("select * from people;");  
        while (rs.next()) {  
            System.out.println("name = " + rs.getString("name"));  
            System.out.println("job = " + rs.getString("occupation"));  
        }  
        rs.close();  
        conn.close();  
	}

}
