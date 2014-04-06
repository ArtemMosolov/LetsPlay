package com.epam.controller.web.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.album.AlbumService;
import com.epam.core.PageBilder;
import com.epam.model.bean.Album;

public class ShowAlbum extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5677369536840397402L;
	private static final String SHOW_ALBUM = "showAlbum.jsp";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		AlbumService service = new AlbumService();
		List<Album> list = null;
		String parametr = null;		
		if((parametr = req.getParameter("artistid"))!=null){
			try{
				list = service.getAllModelByTable(Integer.valueOf(parametr));
				req.setAttribute("artistid", parametr);
			}catch(NumberFormatException e){
				new PageBilder(req, resp).includeAdminJsp(SHOW_ALBUM, "Album");
			}
		}
		if(list == null){
			list = service.getAllModelByTable();
		}
		if(service.getErrors().isEmpty()){
			req.setAttribute("list", list);
		}else{
			req.setAttribute("error", service.getErrors());
		}
		new PageBilder(req, resp).includeAdminJsp(SHOW_ALBUM, "Album");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		AlbumService service = new AlbumService();
		List<Album> list = null;
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
		new PageBilder(req, resp).includeAdminJsp(SHOW_ALBUM,"Album");
	}
	
}
