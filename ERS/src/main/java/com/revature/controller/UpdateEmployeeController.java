package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class UpdateEmployeeController {

	public static void updateEmployeeFN(HttpServletRequest request, HttpServletResponse response) {
		
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee") ;
		
		return;

		
	}
}