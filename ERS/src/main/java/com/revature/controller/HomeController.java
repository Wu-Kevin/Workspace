package com.revature.controller;

import java.io.File;
import java.io.IOException;

import javax.management.modelmbean.ModelMBean;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;

public class HomeController {
	public static String home(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee") ;
		if (loggedEmployee.getIsmanager() == 0) {
			return "home.html";
		}
		return "admin.html";
	}
}
