package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Two extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Two() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("This is from servlet Two\n");
		
		String s1 = getServletConfig().getInitParameter("Quarterback");
		response.getWriter().append(s1);
		
		//getParameter vs getInitParameter
		//getParameter - retrieve information from user/request
		//getInitParameter - retrieve config information web.xml
		//getAttribute - retrieve information from another servlet
		
		HttpSession session = request.getSession();
		String s2 = (String) session.getAttribute("sessionid");
		
		response.getWriter().append(s2);
		
		getServletContext().setAttribute("name",  s1);
		request.getRequestDispatcher("Three").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
