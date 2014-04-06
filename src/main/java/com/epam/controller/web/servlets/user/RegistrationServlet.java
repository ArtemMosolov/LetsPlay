package com.epam.controller.web.servlets.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.RegistrationService;
import com.epam.core.PageBilder;
import com.epam.core.util.Validator;
import com.epam.model.bean.UserModel;

public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String REGISTRATION_JSP = "registration.jsp";
	private static final String HOME_JSP = "index.jsp";
	private HttpSession session;
	private RegistrationService registrationService;
	private UserModel user;

	private boolean checkPassword(String password, String repeatPassword) {
		boolean result = false;

		if (password.equals(repeatPassword)) {
			result = true;
		}

		return result;
	}

	private boolean isEmpty(Map<String, String[]> parametrs, UserModel user) {
		boolean result = false;
		for (String fieldName : parametrs.keySet()) {
			if (Validator.EMPTY_FIELD.validate(parametrs.get(fieldName)[0])) {
				registrationService.getErrors().put(fieldName,
						fieldName + "_empty");
				result = true;
			}
		}
		return result;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		new PageBilder(request, response).includeJsp(REGISTRATION_JSP,
				"Registry");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		registrationService = new RegistrationService();
		session = request.getSession();
		user = new UserModel();
		user.setLogin(request.getParameter("login"));
		user.setEmail(request.getParameter("email"));
		request.setAttribute("login", user.getLogin());
		request.setAttribute("email", user.getEmail());
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		user.setLanguage(session.getAttribute("sessionLocale").toString());

		if (isEmpty(request.getParameterMap(), user)) {
			request.setAttribute("errors", registrationService.getErrors());
			new PageBilder(request, response).includeJsp(REGISTRATION_JSP,
					"Home");
		} else if (checkPassword(password, repeatPassword)) {
			user.setPassword(password);

			if (!registrationService.createUser(user)) {
				request.setAttribute("errors", registrationService.getErrors());
				new PageBilder(request, response).includeJsp(REGISTRATION_JSP,
						"Home");
			} else {
				request.setAttribute("congratulation",
						"CONGRATULATION");
				//new PageBilder(request, response).includeJsp(HOME_JSP, "Home");
				request.getRequestDispatcher("home").forward(request, response);
			}
		} else {
			registrationService.getErrors().put("passwordEqual",
					"Enter_identical_passwords");
			request.setAttribute("errors", registrationService.getErrors());
			new PageBilder(request, response).includeJsp(REGISTRATION_JSP,
					"Home");
		}

	}
}
