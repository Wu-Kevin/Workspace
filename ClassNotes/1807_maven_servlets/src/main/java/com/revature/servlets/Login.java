package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		printHeaderInfo(request, response);
		String s1 = request.getParameter("userid"); //check id of html element //get parameter servlet to servlet, get attribute form to servlet
//		request.getRequestDispatcher("../index.jsp").include(request, response); //where info is sent using include
		request.getRequestDispatcher("../ServletContextConfig").forward(request, response); //forwarding example
//		response.sendRedirect("../ServletContextConfig");
		
//		RequestDispatcher
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void printHeaderInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Enumeration<String> enu = request.getHeaderNames();
		while (enu.hasMoreElements()) {
			String headername = enu.nextElement();
			String headervalue = request.getHeader(headername);
			pw.println("headername " + headername);
			pw.println("headervalue " + headervalue);
			pw.println("<br>");
		}

	}

}
