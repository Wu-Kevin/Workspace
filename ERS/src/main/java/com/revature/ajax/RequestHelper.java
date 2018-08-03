package com.revature.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.ListController;
import com.revature.controller.UpdateEmployeeController;
import com.revature.util.FinalUtil;

public class RequestHelper {
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		switch(request.getRequestURI()) {
		case "/ERS/html/listEmployees.ajax":
			return ListController.listEmployee(request, response);
			
		case "/ERS/html/updateEmployeeFN.ajax":
			return UpdateEmployeeController.updateEmployeeFN(request, response);
		
		case "/ERS/html/updateEmployeeLN.ajax":
			return UpdateEmployeeController.updateEmployeeLN(request, response);
		
		case "/ERS/html/updateEmployeeEM.ajax":
			return UpdateEmployeeController.updateEmployeeEM(request, response);
		
		case "/ERS/html/updateEmployeePW.ajax":
			return UpdateEmployeeController.updateEmployeePW(request, response);
		
		default:
			return new AjaxMessage(FinalUtil.NOT_IMPLEMENTED);
		}
	}
}
