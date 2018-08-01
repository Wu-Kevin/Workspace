package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class One extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public One() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		response.getWriter().append("this is from servlet One\n");
		RequestDispatcher rd = request.getRequestDispatcher("Two");
		HttpSession session = request.getSession();
		session.setAttribute("sessionid",  "12345");
		
//		rd.forward(request,  response); //url doesn't change, doesn't print first getWriter
		rd.include(request,  response); //include shows both getWriter methods()
//		response.sendRedirect("two"); //url changes, doesn't print first getWriter 
	

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
