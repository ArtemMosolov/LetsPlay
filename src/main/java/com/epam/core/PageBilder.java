package com.epam.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class PageBilder {
	private HttpServletRequest request;
	private HttpServletResponse response;

	private static Logger logger = Logger.getLogger(PageBilder.class);

	public PageBilder(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void includeJsp(String nameJsp, String titlePage) {
		try {
			request.setAttribute("url", nameJsp);
			request.setAttribute("title", titlePage);
			request.getRequestDispatcher("/view/default/layout.jsp").forward(
					request, response);

		} catch (ServletException | IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void includeAdminJsp(String nameJsp, String titlePage) {
		try {
			request.setAttribute("url", nameJsp);
			request.setAttribute("title", titlePage);
			request.getRequestDispatcher("/view/admin/layoutAdmin.jsp").forward(
					request, response);

		} catch (ServletException | IOException e) {
			logger.error(e.getMessage());
		}
	}
	@Deprecated
	public void includeAdminJspAxaj(String nameJsp){
		try {
			response.reset();
			request.getRequestDispatcher("/view/admin/"+nameJsp).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
