package com.epam.controller.web.servlets.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.UserService;
import com.epam.core.PageBilder;
import com.epam.model.bean.UserInfo;

public class PersonalSettingsServlet extends HttpServlet {

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

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");
		UserInfo userInfo = userService.getUserInfo(userId);

		getFormTextData(request, userInfo);
		getFormDateData(request, userInfo);

		userInfo.setIdUser(userInfo.getIdUser());
		userInfo.setIdImageUser(userInfo.getIdImageUser());
		userService.updateUserInfo(userInfo);

		request.setAttribute("userInfo", userService.getUserInfo(userId));
		new PageBilder(request, response).includeJsp(SETTINGS_JSP, "Settings");
	}

	private void getFormDateData(HttpServletRequest request, UserInfo userInfo) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date date = dateFormat.parse(request.getParameter("date"));
			if (validateBirthDate(date, request)) {
				userInfo.setDateOfBirth(date);
			} else {
				throw new IllegalArgumentException();
			}
		} catch (ParseException | IllegalArgumentException e) {
			userInfo.setDateOfBirth(null);
		}
	}

	private boolean validateBirthDate(Date date, HttpServletRequest request) {
		boolean result = false;
		Date currentDate = new Date();

		int differentInDays = (int) ((currentDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));

		// not less than 16 years old
		if (differentInDays >= 5844) {
			result = true;
		} else {
			request.setAttribute("birthDateError", "BIRTH_DATE_ERROR");
		}

		return result;
	}

	private void getFormTextData(HttpServletRequest request, UserInfo userInfo) {
		userInfo.setFirstName(request.getParameter("firstName"));
		userInfo.setLastName(request.getParameter("lastName"));
		userInfo.setGender(request.getParameter("gender"));
		userInfo.setCountry(request.getParameter("country"));
		userInfo.setCity(request.getParameter("city"));
		userInfo.setDescription(request.getParameter("userAbout"));
	}

}
