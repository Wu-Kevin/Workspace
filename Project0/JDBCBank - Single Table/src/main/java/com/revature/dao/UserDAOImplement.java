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

	// getUser works
	public User getUser(String username, String password) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "select * from accounts where username = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return new User(rs.getInt("user_id"), rs.getInt("bankaccount_id"), rs.getString("username"),
						rs.getString("password"), rs.getDouble("account"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// getUser overload, in case of duplicates
	public User getUser(int userid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "select * from accounts where user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, userid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return new User(rs.getInt("user_id"), rs.getInt("bankaccount_id"), rs.getString("username"),
						rs.getString("password"), rs.getDouble("account"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	
	// insertUser works
	public boolean insertUser(String username, String password) {

		try {
			User u = new User();

			u.setUserName(username);
			u.setPassword(password);

			Connection conn = Jdbcconnection.getConnection();
			String sql = "call add_user(?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, u.getUserName());
			cs.setString(2, u.getPassword());

			if (cs.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//insertUser overload to add another account to an existing userID
	public boolean insertUser(int userid, String username, String password) {

		try {
			User u = new User();
			u.setUserID(userid);
			u.setUserName(username);
			u.setPassword(password);

			Connection conn = Jdbcconnection.getConnection();
			String sql = "call add_account(?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, u.getUserID());;
			cs.setString(2, u.getUserName());
			cs.setString(3, u.getPassword());

			if (cs.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteUser(int bankid) {
		
		try {
			Connection conn = Jdbcconnection.getConnection();
			
			String sql = "delete from accounts where bankaccount_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, bankid);
			
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<User> allUsers() {
		try {
			Connection conn = Jdbcconnection.getConnection();
			
			String sql = "select * from accounts";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			List <User> accounts = new ArrayList<User>();
			
			while (rs.next()) {
				accounts.add(new User(rs.getInt("user_id"),
						rs.getInt("bankaccount_id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getDouble("account")));
			}
			
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateUser(int bankid, double change) {
		
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "update accounts set account = account + ? where bankaccount_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, change);
			ps.setInt(2, bankid);
			
			ps.executeUpdate();
			return true;

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
