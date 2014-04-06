package com.epam.controller.web.servlets.user.playlist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.user.playlist.PlaylistService;
import com.epam.model.bean.UserPlayList;

public class MusicActionPlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PlaylistService playlistService = new PlaylistService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int songId = 0, playlistId = 0;

		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");

		String action = request.getParameter("action");
		try {
			songId = Integer
					.parseInt(request.getParameter("songId").toString());
			playlistId = Integer.parseInt(request.getParameter("playlistId")
					.toString());
		} catch (NumberFormatException e) {
			response.sendRedirect("home");
			return;
		}

		UserPlayList userPlayList = playlistService.getUserPlaylist(playlistId);

		if ((userPlayList != null) && userPlayList.getIdUser() == userId) {
			if (action.equalsIgnoreCase("add")) {
				playlistService.addSongPlaylist(playlistId, songId);
				playlistService.addCount(userId, songId);

			} else if (action.equalsIgnoreCase("delete")) {
				playlistService.deletePlaylistsSong(playlistId, songId);
				playlistService.removeCount(userId);
			}
			request.setAttribute("playlist",
					playlistService.getUserPlaylist(playlistId));
			request.setAttribute("songs",
					playlistService.getPlaylistContent(playlistId));
		} else {
			request.setAttribute("error", "Illegal operation");
		}

		response.sendRedirect(request.getHeader("referer"));
	}

}
