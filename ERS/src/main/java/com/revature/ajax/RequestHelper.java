package com.revature.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.InsertReimbursementController;
import com.revature.controller.ListController;
import com.revature.controller.ReimbursementListController;
import com.revature.controller.UpdateEmployeeController;
import com.revature.util.FinalUtil;

public class RequestHelper {
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		switch(request.getRequestURI()) {
		case "/ERS/html/listEmployee.ajax":
			return ListController.listEmployee(request, response);
			
		case "/ERS/html/listAllEmployees.ajax":
			return ListController.listAllEmployees(request, response);
			
		case "/ERS/html/updateEmployeeFN.ajax":
			return UpdateEmployeeController.updateEmployeeFN(request, response);
		
		case "/ERS/html/updateEmployeeLN.ajax":
			return UpdateEmployeeController.updateEmployeeLN(request, response);
		
		case "/ERS/html/updateEmployeeEM.ajax":
			return UpdateEmployeeController.updateEmployeeEM(request, response);
		
		case "/ERS/html/updateEmployeePW.ajax":
			return UpdateEmployeeController.updateEmployeePW(request, response);
		
		case "/ERS/html/listReimbursements.ajax":
			return ReimbursementListController.listReimbursement(request, response);
			
		case "/ERS/html/selectReimbursementById.ajax":
			return ReimbursementListController.listReimbursementById(request, response);
		
		case "/ERS/html/listAllReimbursements.ajax":
			return ReimbursementListController.listAllReimbursements(request, response);
			
		case "/ERS/html/insertReimbursement.ajax":
			return InsertReimbursementController.insertReimbursement(request, response);
		
		case "/ERS/html/changeReimbursements.ajax":
			ReimbursementListController.changeReimbursements(request, response);
			
		default:
			return new AjaxMessage(FinalUtil.NOT_IMPLEMENTED);
		}
	}
}
