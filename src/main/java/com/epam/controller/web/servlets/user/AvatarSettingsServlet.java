package com.epam.controller.web.servlets.user;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.UserService;
import com.epam.core.PageBilder;
import com.epam.core.factory.FactoryFolder;
import com.epam.core.filesystem.ParserRequest;
import com.epam.model.bean.UserInfo;

public class AvatarSettingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();

	private static final String SETTINGS_JSP = "settings.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		int userId = (int) session.getAttribute("userId");
		UserInfo userInfo = userService.getUserInfo(userId);

		request.setAttribute("userInfo", userInfo);
		new PageBilder(request, response).includeJsp(SETTINGS_JSP, "Settings");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		int userId = (int) session.getAttribute("userId");

		UserInfo userInfo = userService.getUserInfo(userId);

		ParserRequest parserRequest = new ParserRequest(request);
		parserRequest.setValidateForm(ParserRequest.getValidateImage());
		parserRequest.getParserParametr();

		File f = new FactoryFolder().createUserImageFolder("userImage");
		String fileName = parserRequest.getFileUploadCustom().createFile(f);

		if (fileName != null) {
			parserRequest.getFileUploadCustom().createFile(f);
			userInfo.getImage().setFileName(fileName);
			userService.insertImage(fileName, userInfo);
		}

		request.setAttribute("userInfo", userInfo);
		new PageBilder(request, response).includeJsp(SETTINGS_JSP, "Home");
	}
}
