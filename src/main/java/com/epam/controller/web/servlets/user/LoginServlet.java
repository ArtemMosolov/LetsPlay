package com.epam.controller.web.servlets.user;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.LoginService;
import com.epam.controller.service.user.UserService;
import com.epam.core.PageBilder;
import com.epam.core.util.FormValidator;
import com.epam.model.bean.UserInfo;
import com.epam.model.bean.UserModel;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String USER_PAGE = "/view/default/index.jsp";
	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("show", true);
		new PageBilder(request, response).includeJsp(USER_PAGE, "Login");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// test form validation true / false
		//System.out.println(FormValidator.validateField(request));
		
		String email = request.getParameter("login");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		UserModel user = loginService.brainLogin(email, password, false);

		if (user != null) {
			HttpSession session = request.getSession(false);
			session.setAttribute("userId", user.getIdUser());
			session.setAttribute("userLogin", user.getLogin());
			session.setAttribute("sessionLocale", user.getLanguage());
			session.setAttribute("isAdmin", user.isAdmin());
			
			UserService userService = new UserService();
			UserInfo userInfo = userService.getUserInfo(user.getIdUser());
			if(userInfo != null){
				session.setAttribute("userInfo", userInfo);
			}
			

			response.sendRedirect("home");

		} else {
			request.setAttribute("message", "CHECK_EMAIL_PASSWORD");
			new PageBilder(request, response).includeJsp(USER_PAGE, "Home");
		}
	}
}
