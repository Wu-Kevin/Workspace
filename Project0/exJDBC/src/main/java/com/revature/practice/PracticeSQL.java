package com.revature.practice;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.jdbcutil.jdbcconnection;

public class PracticeSQL {
	public static Connection conn = jdbcconnection.getConnection();
	
	public static void main(String[] args) {
		String sql = "SELECT * FROM PLAYER";
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
			}
		} catch (SQLException e ) {
			e.printStackTrace();
		} 
	}
}
