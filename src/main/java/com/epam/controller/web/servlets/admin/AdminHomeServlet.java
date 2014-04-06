package com.epam.controller.web.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.core.PageBilder;

public class AdminHomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String ADMIN_HOME_JSP = "adminHome.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		new PageBilder(request, response).includeAdminJsp(ADMIN_HOME_JSP,
				"AdminHome");
	}

}
