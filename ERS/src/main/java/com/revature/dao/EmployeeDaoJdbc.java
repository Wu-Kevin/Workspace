package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;
import com.revature.util.LogUtil;

public class EmployeeDaoJdbc implements EmployeeDao{

	private static EmployeeDaoJdbc employeeDaoJdbc;
	
	private EmployeeDaoJdbc () {
	}
	
	
	public static EmployeeDaoJdbc getEmployeeDaoJdbc() {
		if (employeeDaoJdbc == null) {
			employeeDaoJdbc = new EmployeeDaoJdbc ();
		}
		return employeeDaoJdbc;
	}

	@Override
	public void insert(Employee employee) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL ADD_EMPLOYEE(?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, employee.getPassword());
			cs.setString(2, employee.getFirstname());
			cs.setString(3, employee.getLastname());
			cs.setString(4, employee.getEmail());
			cs.setInt(5, employee.getIsmanager());
			cs.execute();
			
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception creating a new customer", e);
		}
	}


	@Override
	public Employee select(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Employee> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getCustomerHash(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
