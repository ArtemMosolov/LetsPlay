package com.epam.controller.service.genre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.genre.GenreDAOImpl;
import com.epam.controller.service.BaseServise;
import com.epam.core.interfaces.DeleteData;
import com.epam.model.bean.Artist;
import com.epam.model.bean.Genre;
import com.epam.model.exception.EmptyListException;

public class GenreService extends BaseServise<Genre> {
	private GenreDeployService genreDeployService;
	private GenreSearchService genreSearchService;
	private GenreDAOImpl genreDAOImpl;

	public GenreService() {
		this(new GenreDeleteService(), new GenreDAOImpl());
		genreDAOImpl = new GenreDAOImpl();

	}

	protected GenreService(DeleteData<Genre> deleteService,
			BaseDAOInterfase<Genre> baseDAOInterfase) {
		super(deleteService, baseDAOInterfase);
	}

	@Override
	public int deploy(Genre model) {
		genreDeployService = new GenreDeployService(genreDAOImpl);
		int result = 0;
		if (model != null) {
			if (model.getIdGenre() == 0) {
				try {
					result = genreDeployService.createRecord(model);
				} catch (SQLException e) {
					setError(getAddError());
					e.printStackTrace();
				}
			} else {
				try {
					genreDeployService.updateRecord(model);
				} catch (SQLException e) {
					setError(getUpdateError());
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	@Override
	public List<Genre> getAllModelByTable() {
		genreSearchService = new GenreSearchService();
		List<Genre> genres = new ArrayList<Genre>();
		try {
			genres = genreSearchService.getAllGenres();
		} catch (NullPointerException e) {
			setError(e.getMessage());
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError(getEmptyError());
			e.printStackTrace();
		}
		return genres;
	}

	public List<Artist> getAllArtistByGenreName(String name) {
		genreSearchService = new GenreSearchService();
		List<Artist> artists = new ArrayList<Artist>();
		try {
			artists = genreSearchService.searchArtistByGenre(name);
		} catch (NullPointerException e) {
			setError(e.getMessage());
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError(getEmptyError());
			e.printStackTrace();
		}
		return artists;
	}

	public List<Artist> getAllArtistByGenreId(int id) {
		genreSearchService = new GenreSearchService();
		List<Artist> artists = new ArrayList<Artist>();
		try {
			artists = genreSearchService.searchArtistByGenreId(id);
		} catch (NullPointerException e) {
			setError(e.getMessage());
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError(getEmptyError());
			e.printStackTrace();
		}
		return artists;
	}
}
