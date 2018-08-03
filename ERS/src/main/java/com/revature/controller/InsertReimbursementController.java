package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.ReimbursementService;

public class InsertReimbursementController {
	public static Object insertReimbursement(HttpServletRequest request, HttpServletResponse response) {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee");
		Reimbursement newReim = new Reimbursement();
		newReim.setEmployeeId(loggedEmployee.getEmployeeId());
		newReim.setAmount(Double.parseDouble(request.getParameter("amt")));
		newReim.setRationale(request.getParameter("rat"));
		return ReimbursementService.getReimbursementService().insertReimbursement(newReim);
		
	}
}
