package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class ListController {

	public static Object listEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee");
		return EmployeeService.getEmployeeService().loginEmployee(loggedEmployee);
	}
	
	public static Object listAllEmployees(HttpServletRequest request, HttpServletResponse response) {
			return EmployeeService.getEmployeeService().listEmployees();
	}
}