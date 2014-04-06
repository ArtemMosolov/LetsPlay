package com.epam.controller.web.servlets.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.RegistrationService;
import com.epam.controller.service.user.RestorePasswordService;
import com.epam.core.PageBilder;
import com.epam.core.util.Validator;

public class RestorePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RestorePasswordService restorePasswordService = new RestorePasswordService();

	private RegistrationService registrationService = new RegistrationService();

	private static final String RESTORE_PASSWORD_PAGE = "restorepassword.jsp";

	private boolean checkPassword(String password, String repeatPassword) {
		boolean result = false;

		if (password.equals(repeatPassword)) {
			result = true;
		}

		return result;
	}

	private boolean isEmpty(Map<String, String[]> parametrs) {
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

		String hash = request.getParameter("hash");
		if (!restorePasswordService.validateHash(hash)) {
			request.setAttribute("error", "ERROR_HASH");
		} else if (!restorePasswordService.checkDate(hash)) {
			request.setAttribute("error", "LINK_IS_NOT_ACTUAL");
		} else {
			HttpSession session = request.getSession(false);
			session.setAttribute("hash", hash);
		}

		new PageBilder(request, response).includeJsp(RESTORE_PASSWORD_PAGE,
				"Restore");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");

		if (isEmpty(request.getParameterMap())) {
			request.setAttribute("errors", registrationService.getErrors());
			new PageBilder(request, response).includeJsp(RESTORE_PASSWORD_PAGE,
					"Home");
		} else if (!checkPassword(password, repeatPassword)) {
			registrationService.getErrors().put("passwordEqual",
					"ERROR_DIFFERENT_PASSWORDS");
			request.setAttribute("errors", registrationService.getErrors());
		} else {
			restorePasswordService.restore(
					(String) session.getAttribute("hash"), password);
			restorePasswordService.deleteHash((String) session
					.getAttribute("hash"));
			request.setAttribute("changePassword", true);
		}

		request.getRequestDispatcher("home").forward(request, response);
	}
}
