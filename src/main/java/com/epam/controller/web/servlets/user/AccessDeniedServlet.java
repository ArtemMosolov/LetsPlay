package com.epam.controller.web.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.core.PageBilder;

public class AccessDeniedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccessDeniedServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		new PageBilder(request, response).includeJsp("accessDenied.jsp",
				"Home");
	}

}
