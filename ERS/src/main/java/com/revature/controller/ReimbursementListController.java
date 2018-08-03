package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;
import com.revature.util.FinalUtil;

public class ReimbursementListController {
	
	public static Object listReimbursement(HttpServletRequest request, HttpServletResponse response) {
		
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee") ;
		if (loggedEmployee.getIsmanager() == 0) {
			return ReimbursementService.getReimbursementService().selectByID(loggedEmployee.getEmployeeId());
		}
		else { 
			return ReimbursementService.getReimbursementService().selectAll();
		}
	}
}
		