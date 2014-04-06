package com.epam.controller.web.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.artist.ArtistService;
import com.epam.core.PageBilder;
import com.epam.model.bean.Artist;

public class ShowArtists extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5831529012349136323L;
	private static final String SHOW_ARTISTS = "showArtists.jsp";
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ArtistService service = new ArtistService();
		List<Artist> list = null;
		list = service.getAllModelByTable();
		if(service.getErrors().isEmpty()){
			req.setAttribute("list", list);
		}else{
			req.setAttribute("error", service.getErrors());
		}
		new PageBilder(req, resp).includeAdminJsp(SHOW_ARTISTS, "Artists");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ArtistService service = new ArtistService();
		String parametr = null;
		List<Artist> list = null;
	
		if((parametr = req.getParameter("searchInput"))!=null){
			list = service.search(parametr);
		}
		req.setAttribute("parametr", parametr);
		if(service.getErrors().isEmpty()){
			req.setAttribute("list", list);
		}else{
			req.setAttribute("error", service.getErrors());
		}
		new PageBilder(req, resp).includeAdminJsp(SHOW_ARTISTS, "Artists");
	}
	
}
