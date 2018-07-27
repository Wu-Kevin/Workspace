package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//    public HelloWorld() {
//        super();
//    }
//	public void init(ServletConfig config) throws ServletException {
//		System.out.println("init method");
//	}
//
//	public void destroy() {
//		System.out.println("destroy method");
//
//	}
//
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("service method");
//
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("get method");
		response.getWriter().append("Hello World!").append(request.getContextPath());
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");;
//		String s1 = "asldkfj";
		String s1 = request.getParameter("text1");
		String s2 = request.getParameter("ssn");

		pw.println("<html><body><div><h1>" + "Hello " + s1 + "! How are you? Your SSN is: " + s2 + "</h1></div></body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post method");
		doGet(request, response);
	}

}
