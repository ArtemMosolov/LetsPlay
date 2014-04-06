package com.epam.controller.web.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.LoginService;
import com.epam.model.bean.UserModel;

public class AdminLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String ADMIN_LOGIN_JSP = "/view/admin/adminLogin.jsp";

	public AdminLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Object userId = session.getAttribute("userId");

		if (userId != null) {
			response.sendRedirect("admin/home");
			return;
		}

		request.getRequestDispatcher(ADMIN_LOGIN_JSP)
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		UserModel user = loginService.brainLogin(login, password, true);

		if (user != null) {
			HttpSession session = request.getSession(false);
			session.setAttribute("userId", user.getIdUser());
			session.setAttribute("userLogin", user.getLogin());
			session.setAttribute("isAdmin", user.isAdmin());
			session.setAttribute("sessionLocale", user.getLanguage());
			response.sendRedirect("admin/home");
		} else {
			request.setAttribute("message", "CHECK_EMAIL_PASSWORD");
			request.getRequestDispatcher(ADMIN_LOGIN_JSP).forward(request,
					response);
		}
	}
}
