package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;
import com.revature.util.FinalUtil;

public class ReimbursementListController {

	public static Object listReimbursement(HttpServletRequest request, HttpServletResponse response) {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee");
		return ReimbursementService.getReimbursementService().selectByID(loggedEmployee.getEmployeeId());
	}

	public static Object listAllReimbursements(HttpServletRequest request, HttpServletResponse response) {
		return ReimbursementService.getReimbursementService().selectAll();
	}
	
	public static Object insertReimbursement(HttpServletRequest request, HttpServletResponse response) {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee");
		Reimbursement newReim = new Reimbursement();
		newReim.setEmployeeId(loggedEmployee.getEmployeeId());
		newReim.setAmount(Double.parseDouble(request.getParameter("amt")));
		newReim.setRationale(request.getParameter("rat"));
		return ReimbursementService.getReimbursementService().insertReimbursement(newReim);
	}
	
	public static Object listReimbursementById(HttpServletRequest request, HttpServletResponse response) {
		return ReimbursementService.getReimbursementService().selectByID(Integer.parseInt(request.getParameter("reimid")));
	}
	
	public static void changeReimbursements(HttpServletRequest request, HttpServletResponse response) {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee");

		ReimbursementService.getReimbursementService().changeReimbursement(
				Integer.parseInt(request.getParameter("changeid")),
				loggedEmployee.getEmployeeId(), 
				Integer.parseInt(request.getParameter("approval"))
				);
	}
	
}
