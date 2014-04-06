package com.epam.controller.web.servlets.user.playlist;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.playlist.PlaylistService;
import com.epam.core.PageBilder;
import com.epam.model.bean.UserPlayList;

public class PlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String PLAYLIST_PAGE = "playlist.jsp";

	private PlaylistService playlistService = new PlaylistService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");

		try {
			int playlistId = Integer.parseInt(request
					.getParameter("playlistId").toString());
			UserPlayList userPlayList = playlistService
					.getUserPlaylist(playlistId);

			if ((userPlayList != null) && userPlayList.getIdUser() == userId) {
				getPlaylistContent(request, response, playlistId);
			} else {
				throw new InvalidParameterException();
			}

		} catch (NumberFormatException | InvalidParameterException e) {
			response.sendRedirect("home");
		}
	}

	private void getPlaylistContent(HttpServletRequest request,
			HttpServletResponse response, int playlistId) {
		request.setAttribute("playlist",
				playlistService.getUserPlaylist(playlistId));
		request.setAttribute("songs",
				playlistService.getPlaylistContent(playlistId));

		new PageBilder(request, response).includeJsp(PLAYLIST_PAGE, "Playlist");
	}
}
