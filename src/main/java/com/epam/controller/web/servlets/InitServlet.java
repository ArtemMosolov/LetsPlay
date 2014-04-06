package com.epam.controller.web.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.taglibs.standard.lang.jstl.test.beans.Factory;

import com.epam.controller.service.album.AblumSearchService;
import com.epam.controller.service.artist.ArtistSearchService;
import com.epam.controller.service.artist.ArtistService;
import com.epam.controller.service.genre.GenreSearchService;
import com.epam.controller.service.song.SongSearchService;
import com.epam.core.factory.FactoryFolder;
import com.epam.core.factory.FactorySearchService;
import com.epam.core.filesystem.CreateFileSystem;
import com.epam.core.filesystem.ReadProperty;
import com.epam.core.loadsrc.LoadModel;
import com.epam.core.util.SearchPriorityParser;
import com.epam.model.bean.Artist;
import com.epam.model.bean.ArtistDescription;

public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = -1714201238822612194L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		LoadModel.getInstance().generateMapModel();
		CreateFileSystem.CORE_FOLDER = new ReadProperty().getFolderName();
		initSearchs();
	}

	private void initSearchs() {
		FactorySearchService factorySearchService = FactorySearchService
				.getInstance();
		factorySearchService.setSearchs(new SearchPriorityParser()
				.getSearchPriority());
		factorySearchService.parserPriority();
		factorySearchService.setSearchables(new SongSearchService());
		factorySearchService.setSearchables(new ArtistSearchService());
		factorySearchService.setSearchables(new AblumSearchService());
		factorySearchService.setSearchables(new GenreSearchService());
		
	}
}