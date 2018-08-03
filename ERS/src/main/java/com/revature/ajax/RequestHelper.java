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
			UpdateEmployeeController.updateEmployeeFN(request, response);
			
		default:
			return new AjaxMessage(FinalUtil.NOT_IMPLEMENTED);
		}
	}
}
