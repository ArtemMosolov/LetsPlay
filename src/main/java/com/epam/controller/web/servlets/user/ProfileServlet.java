package com.epam.controller.web.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.playlist.PlaylistService;
import com.epam.core.PageBilder;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String PROFILE_PAGE = "/view/default/profile.jsp";

	private PlaylistService playlistService = new PlaylistService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");

		request.setAttribute("playlists",
				playlistService.getAllUserPlaylists(userId));
		new PageBilder(request, response).includeJsp(PROFILE_PAGE, "Profile");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}
