package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Three extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Three() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String s1 = (String) getServletContext().getAttribute("name");
		HttpSession session = request.getSession();

		String s2 = (String) session.getAttribute("sessionid");
		response.getWriter().append(s2);
		response.getWriter().append(s1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
