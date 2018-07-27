package com.revature.jdbcutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class jdbcconnection {

	public static void main(String[] args) {
		Connection conn = jdbcconnection.getConnection();
		System.out.println(conn);
	}

	private jdbcconnection() {
		
	}
	public static Connection getConnection() {
		InputStream in = null;

		try {
			
			//
			Properties props = new Properties();
			in = new FileInputStream("src/main/resources/connection.properties");
			props.load(in);;
			
			Connection conn = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			// DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//			// need url, username, password
			String endpoint = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");

			conn = DriverManager.getConnection(endpoint, username, password);
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}