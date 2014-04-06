package com.epam.controller.web.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.song.SongService;
import com.epam.core.PageBilder;
import com.epam.model.bean.Song;

public class ShowSong extends HttpServlet {

	private static final long serialVersionUID = -6878262299153190885L;

	private static final String SHOW_SONG = "showSong.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SongService service = new SongService();
		List<Song> list = null;
		String parametr = null;
		if ((parametr = req.getParameter("albumid")) != null) {
			try {
				list = service.getAllModelByTable(Integer.valueOf(parametr));
				req.setAttribute("albumid", parametr);
			} catch (NumberFormatException e) {
				new PageBilder(req, resp).includeAdminJsp(SHOW_SONG, "Song");
			}
		}else if ((parametr = req.getParameter("nameArtist")) != null) {
			list = service.search(parametr, "Artist");
		}
		if (list == null) {
			list = service.getAllModelByTable();
		}
		if (service.getErrors().isEmpty()) {
			req.setAttribute("list", list);
		} else {
			req.setAttribute("error", service.getErrors());
		}
		new PageBilder(req, resp).includeAdminJsp(SHOW_SONG, "Song");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SongService service = new SongService();
		List<Song> list = null;
		String parametr = null;
		if((parametr = req.getParameter("searchInput"))!=null){
			list = service.search(parametr, null);
		}
		req.setAttribute("parametr", parametr);
		if(service.getErrors().isEmpty()){
			req.setAttribute("list", list);
		}else{
			req.setAttribute("error", service.getErrors());
		}
		new PageBilder(req, resp).includeAdminJsp(SHOW_SONG, "Song");
	}

}
