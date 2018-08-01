package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletContextConfig() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc1 = getServletContext();
//		String s1 = (String) sc1.getAttribute("s1");
		
		response.getWriter().append("From servletcontextconfig user class").append(request.getContextPath());
//		response.getWriter().append(s1);
//		response.setContentType("text/html");
//		PrintWriter pw = response.getWriter();
//		pw.println("<>");
		
		//configuration details of servlets are retrieved
		//using servlet context
		response.getWriter().append(sc1.getInitParameter("Best QB All Time"));
		response.getWriter().append(getServletContext().getInitParameter("Best QB All Time"));
//		response.getWriter().append(getInitParameter("Best QB"));
		//ServletConfig
//		response.getWriter().append(getInitParameter(""));
		
//		response.getWriter().append(getServletConfig().getInitParameter("Best QB All Time"));
		response.getWriter().append(getServletConfig().getInitParameter("Best QB"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
