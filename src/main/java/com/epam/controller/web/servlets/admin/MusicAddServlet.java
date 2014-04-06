package com.epam.controller.web.servlets.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.song.SongService;
import com.epam.core.PageBilder;
import com.epam.core.util.SongFeatures;
import com.epam.model.bean.Song;

public class MusicAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String EDIT = "editMusic.jsp";
	private static final String BROWSER = "showArtists.jsp";
	private SongFeatures features;
	private List<Song> songs;
	private SongService songService;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		new PageBilder(request, response).includeAdminJsp(EDIT,
				"Confirm some fields");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameters = request.getParameterMap();
		features = SongFeatures.getSongFeatures();
		songs = features.validateSongParameters(parameters);
		if (!songs.isEmpty()) {
			songService = new SongService();
			for (Song song : songs) {
				songService.deploy(song);

			}
		}
		new PageBilder(request, response).includeAdminJsp(BROWSER, "Success");
	}
}
