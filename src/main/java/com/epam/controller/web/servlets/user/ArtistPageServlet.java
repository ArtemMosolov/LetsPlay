package com.epam.controller.web.servlets.user;

import java.io.IOException;
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
import com.epam.model.bean.Album;
import com.epam.model.bean.Artist;
import com.epam.model.bean.Song;

public class ArtistPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AlbumService albumService = new AlbumService();

	private ArtistService artistService = new ArtistService();

	private SongService songService = new SongService();

	private PlaylistService playlistService = new PlaylistService();

	private static final String SHOW_ARTIST_PAGE = "showArtist.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Object userId = session.getAttribute("userId");
		if (userId != null) {
			request.setAttribute("playlists",
					playlistService.getAllUserPlaylistsStatistics((int) userId));
		}

		try {
			int idArtist = Integer.valueOf(request.getParameter("idArtist"));
			if (idArtist > 0) {
				Artist artist = getArtist(request, idArtist);
				if (artist != null) {
					getPopularSongs(request, idArtist);
					getPopularAlbums(request, idArtist);
				}
				new PageBilder(request, response).includeJsp(SHOW_ARTIST_PAGE,
						"Artist");
			} else {
				response.sendRedirect("home");
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("home");
		}
	}

	private void getPopularAlbums(HttpServletRequest request, int idArtist) {
		List<Album> albumsForCurrentArtist = albumService
				.getAlbumsByIdArtist(idArtist);
		if (albumService.getErrors().isEmpty()) {
			request.setAttribute("albumsForCurrentArtist",
					albumsForCurrentArtist);
		} else {
			request.setAttribute("AlbumsError", albumService.getErrors());
		}
	}

	private void getPopularSongs(HttpServletRequest request, int idArtist) {
		List<Song> popularSongsForCurrentArtist = songService
				.getPopularSongsByIdArtist(idArtist);
		if (songService.getErrors().isEmpty()) {
			request.setAttribute("popularSongsForCurrentArtist",
					popularSongsForCurrentArtist);
		} else {
			request.setAttribute("SongsError", songService.getErrors());
		}
	}

	private Artist getArtist(HttpServletRequest request, int idArtist) {
		Artist artist = artistService.getModelById(idArtist);
		if (artistService.getErrors().isEmpty()) {
			request.setAttribute("artist", artist);
		} else {
			request.setAttribute("ArtistError", artistService.getErrors());
		}
		return artist;
	}
}
