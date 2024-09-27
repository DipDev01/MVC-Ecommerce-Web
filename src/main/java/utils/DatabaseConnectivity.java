package utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DatabaseConnectivity {
	
	private static BasicDataSource dataSource;
	private static Connection conn;
	
	static {
		dataSource=new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mvcApproach");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
	}
	
	public static Connection getDbConnection() {
		try {
			conn=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}

	public static Connection getDbConnection1() {
		// TODO Auto-generated method stub
		return null;
	}
}
