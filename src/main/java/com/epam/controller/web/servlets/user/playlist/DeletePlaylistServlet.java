package com.epam.controller.web.servlets.user.playlist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.playlist.PlaylistService;
import com.epam.core.PageBilder;
import com.epam.model.bean.UserPlayList;

public class DeletePlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String PROFILE_PAGE = "profile.jsp";

	private PlaylistService playlistService = new PlaylistService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");
		int playlistId = 0;

		try {
			playlistId = Integer.parseInt(request.getParameter("playlistId"));
		} catch (NumberFormatException e) {
			response.sendRedirect("home");
			return;
		}

		UserPlayList userPlayList = playlistService.getUserPlaylist(playlistId);

		if (userPlayList.getIdUser() == userId) {
			playlistService.delete(playlistId);
		}

		request.setAttribute("playlists",
				playlistService.getAllUserPlaylists(userId));
		new PageBilder(request, response).includeJsp(PROFILE_PAGE, "Profile");

	}
}
