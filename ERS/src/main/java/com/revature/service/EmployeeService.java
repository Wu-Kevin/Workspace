package com.revature.service;

import java.util.List;

import com.revature.dao.EmployeeDaoJdbc;
import com.revature.model.Employee;

public class EmployeeService {
	private static EmployeeService employeeService;
	
	private EmployeeService() {
		
	}
	
	public static EmployeeService getEmployeeService() {
		if(employeeService == null) {
			employeeService = new EmployeeService();
		}
		return employeeService;
	}
	
	public void registerEmployee(Employee employee) {
		EmployeeDaoJdbc.getEmployeeDaoJdbc().insert(employee);
	}
	
	public void updateEmployee(Employee employee) {
		EmployeeDaoJdbc.getEmployeeDaoJdbc().update(employee);
	}
	public Employee loginEmployee(Employee employee) {
		return EmployeeDaoJdbc.getEmployeeDaoJdbc().select(employee);
	}
	
	public List<Employee> listEmployees() {
		return EmployeeDaoJdbc.getEmployeeDaoJdbc().selectAll();
	}
}
