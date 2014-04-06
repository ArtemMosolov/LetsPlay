package com.epam.controller.web.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.core.PageBilder;

public class ArtistAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String EDIT_ADD_ARTIST_JSP = "editArtist.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		new PageBilder(request, response).includeJsp(EDIT_ADD_ARTIST_JSP,
				"Artist Add");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
