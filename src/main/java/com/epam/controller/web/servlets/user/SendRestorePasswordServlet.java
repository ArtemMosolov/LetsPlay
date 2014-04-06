package com.epam.controller.web.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.user.SendRestorePassword;
import com.epam.core.PageBilder;

public class SendRestorePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String SEND_PASSWORD_PAGE = "/view/default/sendpassword.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		new PageBilder(request, response).includeJsp(SEND_PASSWORD_PAGE,
				"Restore");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");

		if (email != null) {
			new SendRestorePassword().sendMail(email);
			request.setAttribute("sendPassword", true);
		}

		request.getRequestDispatcher("home").forward(request, response);
	}
}
