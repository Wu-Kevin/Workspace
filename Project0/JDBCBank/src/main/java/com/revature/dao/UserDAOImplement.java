package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.jdbcutil.Jdbcconnection;
import com.revature.model.User;

public class UserDAOImplement implements UserDAO {

	public static UserDAOImplement userDAO;

	private UserDAOImplement() {
	}

	public static UserDAO getDao() {
		if (userDAO == null) {
			userDAO = new UserDAOImplement();
		}
		return userDAO;
	}

	// works
	public User getUser(String username, String password) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "select * from userinformation where username = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// works
	// getUser overload, in case of duplicates
	public User getUser(int userid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "select * from userinformation where user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// works
	public boolean insertUser(String username, String password) {

		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "call add_user(?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			cs.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// works
	public boolean deleteUser(int userid) {

		try {
			Connection conn = Jdbcconnection.getConnection();

			String sql = "delete from userinformation where user_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, userid);

			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// works
	public List<User> allUsers() {
		try {
			Connection conn = Jdbcconnection.getConnection();

			String sql = "select * from userinformation order by user_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> accounts = new ArrayList<User>();

			while (rs.next()) {
				accounts.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password")));
			}

			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateUser(int input, int userid, String username, String password) {
		
		try {
		Connection conn = Jdbcconnection.getConnection();
		String sql = "update userinformation set user_id = ?, username = ?, password = ?  where user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userid);
		ps.setString(2, username);
		ps.setString(3, password);
		ps.setInt(4, input);
		ps.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
}
