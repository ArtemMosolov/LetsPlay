package com.epam.controller.web.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.BaseServise;
import com.epam.controller.service.album.AlbumService;
import com.epam.controller.service.artist.ArtistService;
import com.epam.controller.service.genre.GenreService;
import com.epam.controller.service.song.SongService;
import com.epam.core.PageBilder;

public class DeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2996262545232823911L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String paramert = null;
		BaseServise<?> baseServise = null;
		String back = null;
		if((paramert = req.getParameter("songid"))!= null){
			baseServise = new SongService();
			if(req.getParameter("albumid")!=null){
				back = "albumid";
				paramert = req.getParameter("albumid");
			}
			try{
				baseServise.delete(Integer.valueOf(paramert));
			}catch(NumberFormatException e){
				
			}
		}else if((paramert = req.getParameter("artistid"))!= null){
			baseServise = new ArtistService();
			try{
				baseServise.delete(Integer.valueOf(paramert));
			}catch(NumberFormatException e){
				
			}
		}else if((paramert = req.getParameter("albumid"))!= null){
			baseServise = new AlbumService();
			try{
				baseServise.delete(Integer.valueOf(paramert));
				if(req.getParameter("artistid")!=null){
					back = "artistid";
					paramert = req.getParameter("artistid");
				}
			}catch(NumberFormatException e){
				
			}
		}else if((paramert = req.getParameter("genreid"))!= null){
			baseServise = new GenreService();
			try{
				baseServise.delete(Integer.valueOf(paramert));
			}catch(NumberFormatException e){
				
			}
		}else if((paramert = req.getParameter("idEvent"))!= null){
			baseServise = new SongService();
			try{
				baseServise.delete(Integer.valueOf(paramert));
			}catch(NumberFormatException e){
			}
		}
		if(back!=null){
			resp.sendRedirect(req.getHeader("Referer")+"?"+back+"="+paramert);
		}else{
			resp.sendRedirect(req.getHeader("Referer"));
		}
	}
}
