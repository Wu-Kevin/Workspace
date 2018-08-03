package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class ListController {

	public static Object listEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee") ;
		if (loggedEmployee.getIsmanager() == 0) {
			return EmployeeService.getEmployeeService().loginEmployee(loggedEmployee);
		}
		else { 
			return EmployeeService.getEmployeeService().listEmployees();
		}
	}
}