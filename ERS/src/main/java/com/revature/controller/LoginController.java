package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;
import com.revature.util.FinalUtil;

public class LoginController {
	public static String login(HttpServletRequest request) {
		
		//GET logic
		if(request.getMethod().equals(FinalUtil.HTTP_GET)) {
			return "login.html";
		}
		
		//POST logic
		//i have userid and password
		
		Employee loggedEmployee = new Employee();
		
		loggedEmployee.setEmployeeId(Integer.parseInt(request.getParameter("userid")));
		loggedEmployee.setPassword(request.getParameter("password"));
		loggedEmployee = EmployeeService.getEmployeeService().loginEmployee(loggedEmployee);
		
		// Wrong Credentials
		if(loggedEmployee.getEmployeeId() == 0) {
			return "login.html";
		}
		else {
			/* Storing loggedCustomer to current session
			SESSION SCOPE IS AVAILABLE ONLY IN THIS REQUEST (CLIENT) */
			request.getSession().setAttribute("loggedEmployee", loggedEmployee);
			
			//Forward user to hit another controller
			return "home.do";
		}
	}
}
