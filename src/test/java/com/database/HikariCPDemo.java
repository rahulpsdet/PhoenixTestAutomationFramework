package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDemo {

	public static void main(String[] args) throws SQLException {
//		public static void createDBConnection() throws SQLException {
			HikariConfig hikariConfig= new HikariConfig();
			
			hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
			hikariConfig.setUsername(ConfigManager.getProperty("DB_USERNAME"));
			hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
			hikariConfig.setMaximumPoolSize(10);
			hikariConfig.setMinimumIdle(2);
			hikariConfig.setConnectionTimeout(10000);
			hikariConfig.setIdleTimeout(10000);
			hikariConfig.setMaxLifetime(1800000);// 30 mins
			hikariConfig.setPoolName("Phoenix Test AUtomation Frameworkn Pool");
			
			HikariDataSource ds = new HikariDataSource(hikariConfig);
			Connection con=ds.getConnection();
			
			System.out.println(con);
			
			Statement statement = con.createStatement();
			
			ResultSet result=statement.executeQuery("Select first_name,last_name from tr_customer;");
			
			while(result.next()) {
				System.out.println(result.getString("first_name") + " " + result.getString("last_name"));
			}
			
			ds.close();
		
	}
	
}
