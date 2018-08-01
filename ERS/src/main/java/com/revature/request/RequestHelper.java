package com.revature.request;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.HomeController;
import com.revature.controller.LoginController;

public class RequestHelper {
	public static String process(HttpServletRequest request) {
		switch(request.getRequestURI()) {
		case "../login.do":
			return LoginController.login(request);
		case"../home.do":
			return HomeController.home(request);
		default:
			return "404.jsp";
		}
	}
}
