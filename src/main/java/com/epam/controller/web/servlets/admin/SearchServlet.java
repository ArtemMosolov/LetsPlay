package com.epam.controller.web.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.SearchService;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 5607001377106371967L;

	private static final String BROWSER_PAGE = "/view/admin/browser.jsp";
	
	private static final String RESULT_PAGE = "/view/default/result.jsp";
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SearchService searchService = new SearchService();
		searchService.search(request.getParameter("searchInput"));
		request.setAttribute("searchList", searchService.getResult());
		if(request.getParameter("isadmin")==null){
			request.getRequestDispatcher(BROWSER_PAGE).include(request, response);
		}else{
			request.getRequestDispatcher(RESULT_PAGE).include(request, response);
		}
	}

}
