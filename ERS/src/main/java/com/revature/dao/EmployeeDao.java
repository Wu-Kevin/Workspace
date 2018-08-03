package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	public void insert(Employee employee);
	
	public void update(Employee employee);

	public Employee select(Employee employee);

	public List<Employee> selectAll();
}
