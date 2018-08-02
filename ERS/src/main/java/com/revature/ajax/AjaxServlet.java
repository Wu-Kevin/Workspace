package com.revature.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.request.RequestHelper;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		/* Sets content to be written in PrintWriter as JSON */
//		response.setContentType("application/json");
//		
//		/* As you can see, any type of object can be written as a JSON 
//		 * Jackson specifically requires that objects to be transformed
//		 * have getters for private variables */
//		response.getWriter().write(
//				new ObjectMapper().writeValueAsString(RequestHelper.process(request, response)));
		
		request.getRequestDispatcher(RequestHelper.process(request, response));

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("application/json");
//		
//		response.getWriter().write(
//				new ObjectMapper().writeValueAsString(RequestHelper.process(request, response)));
		request.getRequestDispatcher(RequestHelper.process(request)).forward(request,response);

	}

}
