package com.epam.controller.web.servlets.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.album.AlbumService;
import com.epam.core.PageBilder;
import com.epam.core.factory.FactoryFolder;
import com.epam.core.filesystem.ParserRequest;
import com.epam.model.bean.Album;
import com.epam.model.bean.AlbumDescription;
import com.epam.model.bean.Image;

public class DeployAlbumServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String EDIT_ADD_ALBUM_JSP = "editAlbum.jsp";
	private Album album = new Album();
	private AlbumService albumService = new AlbumService();
	private int artistId;
	private int albumId;
	private HashMap<String, String> parametrs;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			artistId = Integer.parseInt(request.getParameter("artistid"));
			request.setAttribute("artistid", artistId);
		} catch (NumberFormatException e) {
			artistId = 0;
		}

		try {
			albumId = Integer.parseInt(request.getParameter("albumid"));
		} catch (NumberFormatException e) {
			albumId = 0;
		}

		if (albumId != 0) {
			album = albumService.getModelById(albumId);
			artistId = album.getIdArtist();
			request.setAttribute("album", album);
		}

		new PageBilder(request, response).includeAdminJsp(EDIT_ADD_ALBUM_JSP,
				"Add Album");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ParserRequest parserRequest = new ParserRequest(request);
		parserRequest.setValidateForm(2);
		parserRequest.getParserParametr();
		if (parserRequest.getInvalidFields().isEmpty()) {

			parametrs = parserRequest.getHashMapParametr();

			Album album = new Album();

			album.setNameAlbum(parametrs.get("albumName"));
			album.setIdArtist(artistId);
			album.setIdAlbum(albumId);
			AlbumDescription albumDescription = new AlbumDescription();
			albumDescription.setTitleAlbum(parametrs.get("albumTitle"));
			albumDescription.setDescriptionAlbum(parametrs.get("about"));

			File existingAlbumFolderPath = new FactoryFolder().getBilderPath()
					.getImagePathFolderAlbum(String.valueOf(artistId),
							String.valueOf(albumId));
			if (existingAlbumFolderPath != null) {
				if (!parserRequest.getForLoad().isEmpty()) {
					String currentImagePath = parserRequest
							.getFileUploadCustom().createFile(
									existingAlbumFolderPath);
					Image image = new Image();
					image.setFileName(currentImagePath);
					albumDescription.setImage(image);
					album.setAlbumDescription(albumDescription);

				} else {

					int test = Integer.parseInt(parametrs.get("idImage"));

					albumDescription.setIdImage(test);
					album.setAlbumDescription(albumDescription);

				}
			} else {
				album.setAlbumDescription(albumDescription);

				int id = albumService.deploy(album);
				album.setIdAlbum(id);
				File createdAlbum = new FactoryFolder().createAlbumFolder(
						String.valueOf(artistId), String.valueOf(id));
				String createdAlbumPath = parserRequest.getFileUploadCustom()
						.createFile(createdAlbum);
				Image image = new Image();
				image.setFileName(createdAlbumPath);
				albumDescription.setImage(image);

			}
			album.setAlbumDescription(albumDescription);
			albumService.deploy(album);
			response.sendRedirect("showAlbum" + "?" + "idArtist" + "="
					+ artistId);
		} else {
			request.setAttribute("Error", "Something wrong.");
			request.setAttribute("invalidFields",
					parserRequest.getInvalidFields());
			request.setAttribute("album", album);
			new PageBilder(request, response).includeAdminJsp(
					EDIT_ADD_ALBUM_JSP, "ready");
		}

	}
}
