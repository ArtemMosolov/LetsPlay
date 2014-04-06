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

public class CreatePlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String PROFILE_PAGE = "profile.jsp";

	private PlaylistService playlistService = new PlaylistService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int playlistId = 0;

		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");

		try {
			playlistId = Integer.parseInt(request.getParameter("playlistId"));
		} catch (NumberFormatException e) {
			response.sendRedirect("home");
			return;
		}

		request.setAttribute("playlist",
				playlistService.getUserPlaylist(playlistId));
		request.setAttribute("playlists",
				playlistService.getAllUserPlaylists(userId));
		request.setAttribute("edit", true);

		new PageBilder(request, response).includeJsp(PROFILE_PAGE, "Profile");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int playlistId = 0;

		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userId");

		String namePlaylist = request.getParameter("name");
		String playlist = request.getParameter("playlistId");

		try {
			playlistId = getPlaylistId(request, response, playlistId, playlist);
		} catch (NumberFormatException e) {
			response.sendRedirect("home");
			return;
		}

		playlistService.deploy(new UserPlayList().setIdUser(userId)
				.setNamePlayList(namePlaylist).setIdUserPlayList(playlistId));

		request.setAttribute("playlists",
				playlistService.getAllUserPlaylists(userId));
		new PageBilder(request, response).includeJsp(PROFILE_PAGE, "Profile");
	}

	private int getPlaylistId(HttpServletRequest request,
			HttpServletResponse response, int playlistId, String playlist)
			throws IOException {
		try {
			if (playlist.isEmpty()) {
				playlistId = 0;
			} else {
				playlistId = Integer.parseInt(playlist);
			}
		} catch (NumberFormatException e) {
			throw e;
		}
		return playlistId;
	}

}
