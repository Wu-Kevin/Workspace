package com.revature.request;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.revature.controller.HomeController;
import com.revature.controller.LoginController;

public class RequestHelper {
	public static String process(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		switch(request.getRequestURI()) {
		case "/ERS/html/login.do":
			return LoginController.login(request);
		case "/ERS/html/home.do":
			return HomeController.home(request);
		default:
			return "404.jsp";
		}
	}
}
