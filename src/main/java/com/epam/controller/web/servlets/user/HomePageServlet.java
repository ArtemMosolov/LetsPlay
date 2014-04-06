package com.epam.controller.web.servlets.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.album.AlbumService;
import com.epam.controller.service.artist.ArtistService;
import com.epam.controller.service.song.SongService;
import com.epam.controller.service.user.playlist.PlaylistService;
import com.epam.core.PageBilder;
import com.epam.core.factory.FactorySearchService;
import com.epam.model.bean.Album;
import com.epam.model.bean.Artist;
import com.epam.model.bean.Song;

public class HomePageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String HOME_JSP = "index.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AlbumService albumService = new AlbumService();
		ArtistService artistService = new ArtistService();
		SongService songService = new SongService();
		PlaylistService playlistService = new PlaylistService();

		FactorySearchService.getInstance();
		HashMap<String, List<?>> hashMap = new HashMap<>();
		HttpSession session = request.getSession(false);
		Object userId = session.getAttribute("userId");

		if (userId != null) {
			Boolean isAdmin = (boolean) session.getAttribute("isAdmin");

			if (isAdmin != null && isAdmin.booleanValue()) {
				response.sendRedirect(request.getContextPath() + "/admin/home");
				return;
			} else {
				request.setAttribute("playlists", playlistService
						.getAllUserPlaylistsStatistics((int) userId));
			}
		}

		List<Album> popularAlbumsList = albumService.getPopularAlbums();
		List<Artist> popularArtistsList = artistService.getPopularArtist();
		List<Song> popularSongsList = songService.getPopularSongs();

		if (albumService.getErrors().isEmpty()) {
			request.setAttribute("popularAlbumsList", popularAlbumsList);
		} else {
			request.setAttribute("AlbumsError", albumService.getErrors());
		}

		if (artistService.getErrors().isEmpty()) {
			request.setAttribute("popularArtistsList", popularArtistsList);
		} else {
			request.setAttribute("ArtistsError", artistService.getErrors());
		}

		if (songService.getErrors().isEmpty()) {
			request.setAttribute("popularSongsList", popularSongsList);
		} else {
			request.setAttribute("SongsError", songService.getErrors());
		}

		new PageBilder(request, response).includeJsp(HOME_JSP, "Home");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
