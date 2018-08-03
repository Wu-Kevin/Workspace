package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;
import com.revature.util.LogUtil;

public class ReimbursementDaoJdbc implements ReimbursementDao{

	private static ReimbursementDaoJdbc reimbursementDaoJdbc;
	
	private ReimbursementDaoJdbc () {
	}
	
	
	public static ReimbursementDaoJdbc getReimbursementDaoJdbc() {
		if (reimbursementDaoJdbc == null) {
			reimbursementDaoJdbc = new ReimbursementDaoJdbc ();
		}
		return reimbursementDaoJdbc;
	}

	@Override
	public Reimbursement insert(Reimbursement reimbursement) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL ADD_REIMBURSEMENT (?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, reimbursement.getEmployeeId());
			cs.setDouble(2, reimbursement.getAmount());
			cs.setString(3, reimbursement.getRationale());
			cs.execute();
			
			sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID = ?"
					+ "AND REQUEST_AMOUNT = ?"
					+ "AND RATIONALE = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbursement.getEmployeeId());
			ps.setDouble(2, reimbursement.getAmount());
			ps.setString(3, reimbursement.getRationale());
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				return new Reimbursement(
						rs.getInt("REIMBURSEMENT_ID"),
						rs.getInt("EMPLOYEE_ID"),
						rs.getDouble("REQUEST_AMOUNT"),
						rs.getString("RATIONALE"),
						rs.getInt("APPROVAL"),
						rs.getInt("APPROVAL_ID")
						);
			}
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception creating a new transaction", e);
		}
		return new Reimbursement();
	}

	@Override
	public void update(int approval, Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reimbursement select(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> selectByID(int employeeid) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID = ? ORDER BY REIMBURSEMENT_ID";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeid);
			ResultSet rs = ps.executeQuery();

			List<Reimbursement> reimbursementList = new ArrayList<>();
			while(rs.next()) {
				reimbursementList.add(new Reimbursement(
						rs.getInt("REIMBURSEMENT_ID"),
						rs.getInt("EMPLOYEE_ID"),
						rs.getDouble("REQUEST_AMOUNT"),
						rs.getString("RATIONALE"),
						rs.getInt("APPROVAL"),
						rs.getInt("APPROVAL_ID")
						));
			}

			return reimbursementList;
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception selecting all employees", e);
		} 
		return new ArrayList<>();
	}

	@Override
	public List<Reimbursement> selectAll() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT ORDER BY REIMBURSEMENT_ID";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Reimbursement> reimbursementList = new ArrayList<>();
			while(rs.next()) {
				reimbursementList.add(new Reimbursement(
						rs.getInt("REIMBURSEMENT_ID"),
						rs.getInt("EMPLOYEE_ID"),
						rs.getDouble("REIMBURSEMENT_ID"),
						rs.getString("RATIONALE"),
						rs.getInt("APPROVAL"),
						rs.getInt("APPROVAL_ID")
						));
			}

			return reimbursementList;
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception selecting all employees", e);
		} 
		return new ArrayList<>();
	}
	
}
