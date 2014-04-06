package com.epam.controller.web.servlets.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.controller.service.album.AlbumService;
import com.epam.controller.service.song.SongService;
import com.epam.controller.service.user.playlist.PlaylistService;
import com.epam.core.PageBilder;
import com.epam.model.bean.Album;
import com.epam.model.bean.Song;

public class AlbumPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String SHOW_ALBUM_PAGE = "showAlbum.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SongService songService = new SongService();
		AlbumService albumService = new AlbumService();
		PlaylistService playlistService = new PlaylistService();

		HttpSession session = request.getSession(false);
		Object userId = session.getAttribute("userId");
		if (userId != null) {
			request.setAttribute("playlists",
					playlistService.getAllUserPlaylistsStatistics((int) userId));
		}

		try {
			int idAlbum = Integer.valueOf(request.getParameter("idAlbum"));
			if (idAlbum > 0) {
				Album album = getAlbum(request, albumService, idAlbum);
				if (album != null) {
					getPopularSongs(request, songService, idAlbum);
					getPopularAlbums(request, albumService, album);
				}

				new PageBilder(request, response).includeJsp(SHOW_ALBUM_PAGE,
						"Artist");
			} else {
				response.sendRedirect("home");
			}
		} catch (NumberFormatException e) {
			response.sendRedirect("home");
		}
	}

	private void getPopularAlbums(HttpServletRequest request,
			AlbumService albumService, Album album) {
		List<Album> albumsForCurrentArtist = albumService
				.getAlbumsByIdArtist(album.getIdArtist());
		if (albumService.getErrors().isEmpty()) {
			request.setAttribute("albumsForCurrentArtist",
					albumsForCurrentArtist);
		} else {
			request.setAttribute("AlbumsError", albumService.getErrors());
		}
	}

	private void getPopularSongs(HttpServletRequest request,
			SongService songService, int idAlbum) {
		List<Song> popularSongsForCurrentAlbum = songService
				.getPopularSongsByIdAlbum(idAlbum);
		if (songService.getErrors().isEmpty()) {
			request.setAttribute("popularSongsForCurrentAlbum",
					popularSongsForCurrentAlbum);
		} else {
			request.setAttribute("SongsError", songService.getErrors());
		}
	}

	private Album getAlbum(HttpServletRequest request,
			AlbumService albumService, int idAlbum) {
		Album album = albumService.getAlbumByIdAlbum(idAlbum);
		if (albumService.getErrors().isEmpty()) {
			request.setAttribute("album", album);
		} else {
			request.setAttribute("AlbumError", albumService.getErrors());
		}
		return album;
	}
}
