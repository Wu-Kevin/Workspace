package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class UpdateEmployeeController {

	public static Object updateEmployeeFN(HttpServletRequest request, HttpServletResponse response) {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee") ;
		loggedEmployee.setFirstname(request.getParameter("FN"));
		EmployeeService.getEmployeeService().updateEmployee(loggedEmployee);
		request.getSession().setAttribute("loggedEmployee", loggedEmployee);
		return loggedEmployee;
	}
	
	public static Object updateEmployeeLN(HttpServletRequest request, HttpServletResponse response) {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee") ;
		loggedEmployee.setLastname(request.getParameter("LN"));
		EmployeeService.getEmployeeService().updateEmployee(loggedEmployee);
		request.getSession().setAttribute("loggedEmployee", loggedEmployee);
		return loggedEmployee;
	}
	
	public static Object updateEmployeeEM(HttpServletRequest request, HttpServletResponse response) {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee") ;
		loggedEmployee.setEmail(request.getParameter("EM"));
		EmployeeService.getEmployeeService().updateEmployee(loggedEmployee);
		request.getSession().setAttribute("loggedEmployee", loggedEmployee);
		return loggedEmployee;
	}
	
	public static Object updateEmployeePW(HttpServletRequest request, HttpServletResponse response) {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee") ;
		loggedEmployee.setPassword(request.getParameter("PW"));
		EmployeeService.getEmployeeService().updateEmployee(loggedEmployee);
		request.getSession().setAttribute("loggedEmployee", loggedEmployee);
		return loggedEmployee;
	}
	
	
}