package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.jdbcutil.Jdbcconnection;
import com.revature.model.Account;

public class AccountDAOImplement implements AccountDAO{

	private static AccountDAO accountDAO;
	
	private AccountDAOImplement() {
		
	}
	
	public static AccountDAO getActDao() {
		if (accountDAO == null) {
			accountDAO = new AccountDAOImplement();
		}
		return accountDAO;
	}

	//getaccount with id works
	public Account getAccount(int bankid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "select * from bankaccount where bankaccount_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  bankid);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return new Account(rs.getInt("bankaccount_id"), rs.getInt("user_id"), 
						rs.getDouble("account"));
			}

		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	//works
	public boolean insertAccount(int userid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "call add_account(?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1,  userid);
			cs.executeQuery();
			return true;
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	//DOUBLE CHECK
	public boolean deleteAccount(int bankid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "delete from bankaccount where bankaccount_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bankid);
			ps.executeQuery();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
		}		return false;
	}

	//works
	public List<Account> allAccounts() {
		try {
			List<Account> accountList = new ArrayList<Account>();
			Connection conn = Jdbcconnection.getConnection();
			String sql = "select * from bankaccount order by bankaccount_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				accountList.add(new Account(rs.getInt("bankaccount_id"), rs.getInt("user_id"), rs.getDouble("account")));
			}
			
			return accountList;
		} catch (SQLException e){
			e.printStackTrace();
		}		return null;
	}

	//works
	public boolean updateAccount(int bankid, double change) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "update bankaccount set account = account + ? where bankaccount_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, change);
			ps.setInt(2, bankid);
			
			ps.execute();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
		}		return false;
	}

}
