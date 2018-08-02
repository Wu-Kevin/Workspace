package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ajax.AjaxMessage;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;
import com.revature.util.FinalUtil;

public class ListController {

	public static Object listEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee") ;
		if (loggedEmployee.getIsmanager() == 0) {
			return EmployeeService.getEmployeeService().loginEmployee(loggedEmployee);
		}
		else { //NEED TO IMPLEMENT LIST RETURN
			return new AjaxMessage(FinalUtil.USERNAME_AVAILABLE);
		}
	}
}
