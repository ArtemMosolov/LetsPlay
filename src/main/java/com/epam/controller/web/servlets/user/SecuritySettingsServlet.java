package com.epam.controller.web.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.UserService;
import com.epam.core.PageBilder;
import com.epam.core.util.MD5Encrypter;
import com.epam.core.util.Validator;
import com.epam.model.bean.UserInfo;
import com.epam.model.bean.UserModel;

public class SecuritySettingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String SETTINGS_JSP = "settings.jsp";

	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");
		UserInfo userInfo = userService.getUserInfo(userId);

		request.setAttribute("userInfo", userInfo);
		new PageBilder(request, response).includeJsp(SETTINGS_JSP, "Settings");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");

		if (validatePassword(password, request)
				&& checkPassword(password, confirmPassword, request)) {
			UserModel user = userService.getUserById(userId);
			MD5Encrypter md5 = new MD5Encrypter();
			user.setPassword(md5.encrypt(password));
			userService.updateUser(user);
		}

		request.setAttribute("userInfo", userService.getUserInfo(userId));
		new PageBilder(request, response).includeJsp(SETTINGS_JSP, "Settings");
	}

	private boolean checkPassword(String password, String confirmPassword,
			HttpServletRequest request) {
		boolean result = false;
		if (password.equals(confirmPassword)) {
			result = true;
		} else {
			request.setAttribute("passwordEqual", "Enter_identical_passwords");
		}
		return result;
	}

	private boolean validatePassword(String password, HttpServletRequest request) {
		boolean result = false;
		if (Validator.USER_PASSWORD.validate(password)) {
			result = true;
		} else {
			request.setAttribute("passwordWrong", "ERROR_INVALID_PASSWORD");
		}
		return result;
	}
}
