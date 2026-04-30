package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemmo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

//		Connection connection = DriverManager.getConnection("jdbc:mysql://64.227.160.186 :3306", "srdev_ro_automation","Srdev@123");
		DataBaseManager.createConnection();

	
	}}


