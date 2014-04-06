package com.epam.controller.web.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.UserService;
import com.epam.model.bean.UserModel;

public class LocaleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String language = request.getParameter("language");

		HttpSession session = request.getSession(false);
		session.setAttribute("sessionLocale", language);

		Object userId = session.getAttribute("userId");
		if (userId != null) {
			UserModel user = userService.getUserById((int) userId);
			user.setLanguage(language);
			userService.updateUser(user);
		}

		response.sendRedirect(request.getHeader("referer"));
	}
}
