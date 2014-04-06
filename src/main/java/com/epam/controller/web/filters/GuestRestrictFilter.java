package com.epam.controller.web.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GuestRestrictFilter extends BaseFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			Object userId = session.getAttribute("userId");

			if (userId == null) {
				chain.doFilter(request, response);
				return;
			} else if (userId != null) {
				Boolean isAdmin = (boolean) session.getAttribute("isAdmin");
				homeSendRedirect(response, request, isAdmin);
				return;
			}
		}
		response.sendRedirect(request.getContextPath() + "/accessDenied");
	}

	private void homeSendRedirect(HttpServletResponse response,
			HttpServletRequest request, Boolean isAdmin) throws IOException {
		if ((isAdmin != null) && !isAdmin.booleanValue()) {
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(request.getContextPath() + "/admin/home");
		}
	}

}
