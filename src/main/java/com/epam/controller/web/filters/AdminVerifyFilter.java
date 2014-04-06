package com.epam.controller.web.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminVerifyFilter extends BaseFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			Object userId = session.getAttribute("userId");

			if (userId != null) {
				Boolean isAdmin = (boolean) session.getAttribute("isAdmin");

				if ((isAdmin != null) && isAdmin.booleanValue()) {
					chain.doFilter(request, response);
					return;
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/accessDenied");

	}
}
