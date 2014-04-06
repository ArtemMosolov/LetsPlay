package com.epam.controller.web.servlets.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.artist.ArtistService;
import com.epam.core.PageBilder;
import com.epam.core.factory.FactoryFolder;
import com.epam.core.filesystem.ParserRequest;
import com.epam.model.bean.Artist;
import com.epam.model.bean.ArtistDescription;
import com.epam.model.bean.Image;

public class DeployArtistServlet extends HttpServlet {
	private Artist artist = new Artist();
	private ArtistService artistService = new ArtistService();
	private int artistId;
	private HashMap<String, String> parametrs;
	private static final String EDIT_ADD_ARTIST_JSP = "editArtist.jsp";

	private static final long serialVersionUID = 4311444085283768966L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			artistId = Integer.parseInt(req.getParameter("artistid"));
		} catch (NumberFormatException e) {
			artistId = 0;
		}
		if (artistId != 0) {
			artist = artistService.getModelById(artistId);
			req.setAttribute("artist", artist);
		}
		new PageBilder(req, resp)
				.includeAdminJsp(EDIT_ADD_ARTIST_JSP, "Artist");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		ParserRequest parserRequest = new ParserRequest(request);
		parserRequest.setValidateForm(ParserRequest.getValidateImage());
		parserRequest.getParserParametr();
		if (parserRequest.getInvalidFields().isEmpty()) {
			parametrs = parserRequest.getHashMapParametr();
			Artist artist = new Artist();
			ArtistService artistService = new ArtistService();
			artist.setNameArtist(parametrs.get("artistName"));
			ArtistDescription artistDescription = new ArtistDescription();
			artistDescription.setArtistDescription(parametrs.get("about"));
			artistDescription.setTitle(parametrs.get("title"));
			artist.setIdArtist(artistId);
			
			String artistId = parametrs.get("artistId");
			
			File existingArtistFolderPath = new FactoryFolder().getBilderPath()
					.getImagePathFolderArtist(parametrs.get("artistId"));
			if (existingArtistFolderPath != null) {
				if (!parserRequest.getForLoad().isEmpty()) {
					String pathToCurrentAvatar = parserRequest
							.getFileUploadCustom().createFile(
									existingArtistFolderPath);
					Image image = new Image();
					image.setFileName(pathToCurrentAvatar);
					artistDescription.setImage(image);
				} else {
					artistDescription.setIdImage(Integer.parseInt(parametrs
							.get("idImage")));

				}

			} else {
				artist.setArtistDescription(artistDescription);
				int id = artistService.deploy(artist);
				artist.setIdArtist(id);
				File createdArtist = new FactoryFolder()
						.createArtistFolder(String.valueOf(id));
				String pathToCreatedArtist = parserRequest
						.getFileUploadCustom().createFile(createdArtist);
				Image image = new Image();
				image.setFileName(pathToCreatedArtist);
				artistDescription.setImage(image);

			}
			artist.setArtistDescription(artistDescription);
			int id = artistService.deploy(artist);
			artist = artistService.getModelById(artist.getIdArtist());
			resp.sendRedirect("showArtist");

		} else {
			request.setAttribute("Error", "error");
			request.setAttribute("invalidFields",
					parserRequest.getInvalidFields());
			request.setAttribute("artist", artist);
			new PageBilder(request, resp).includeAdminJsp(EDIT_ADD_ARTIST_JSP,
					"Artist");
		}

	}
}
