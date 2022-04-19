package com.management.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static final String DB_URL="jdbc:mysql://localhost:3306/learnersacademy";
	public static final String USERNAME="root";
	public static final String PASSWORD="javalearn22@";
	public static Connection getConn() throws ClassNotFoundException, SQLException {
		//register my driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//establish connection with DB
		Connection con=DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		return con;
	}
}
