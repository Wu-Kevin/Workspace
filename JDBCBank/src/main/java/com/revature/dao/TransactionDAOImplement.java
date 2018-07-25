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
import com.revature.model.Transaction;

public class TransactionDAOImplement implements TransactionDAO {

	private static TransactionDAO transDAO;

	private TransactionDAOImplement() {

	}

	public static TransactionDAO getTransDao() {
		if (transDAO == null) {
			transDAO = new TransactionDAOImplement();
		}
		return transDAO;
	}

	public Transaction getTransaction(int transid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "select * from transactions where transaction_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, transid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return new Transaction(rs.getInt("transaction_id"), 
						rs.getString("transaction_type"),
						rs.getDouble("transaction_amount"),
						rs.getDouble("transaction_start"),
						rs.getDouble("transaction_end"),
						rs.getInt("bankaccount_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Transaction> getTransactionUser(int bankid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			List<Transaction> transList = new ArrayList<Transaction>();
			String sql = "select * from transactions where bankaccount_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, bankid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				transList.add(new Transaction(rs.getInt("transaction_id"), 
						rs.getString("transaction_type"),
						rs.getDouble("transaction_amount"),
						rs.getDouble("balance_start"),
						rs.getDouble("balance_end"),
						rs.getInt("bankaccount_id")));
			}
			return transList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertTransaction(String type, double amount, double start, double end, int bankid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "call add_transaction(?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, type);
			cs.setDouble(2,  amount);
			cs.setDouble(3,  start);
			cs.setDouble(4,  end);
			cs.setInt(5, bankid);
			cs.executeQuery();
			return true;
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteTransaction(int transid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "delete from transactions where transaction_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, transid);
			ps.executeQuery();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
		}		return false;
	}

	public boolean deleteTransactionUser(int bankid) {
		try {
			Connection conn = Jdbcconnection.getConnection();
			String sql = "delete from transactions where bankaccount_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bankid);
			ps.executeUpdate();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
		}		return false;
	}
	
	
	public List<Transaction> allTransactions() {
		try {
			Connection conn = Jdbcconnection.getConnection();
			List<Transaction> transList = new ArrayList<Transaction>();
			String sql = "select * from transactions order by transaction_id asc";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				transList.add(new Transaction(rs.getInt("transaction_id"), 
						rs.getString("transaction_type"),
						rs.getDouble("transaction_amount"),
						rs.getDouble("balance_start"),
						rs.getDouble("balance_end"),
						rs.getInt("bankaccount_id")));
			}
			return transList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



}