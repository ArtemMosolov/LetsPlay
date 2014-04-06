package com.epam.controller.service.genre;

import java.util.List;

import com.epam.controller.dao.artist.ArtistDAOImpl;
import com.epam.controller.dao.genre.GenreDAOImpl;
import com.epam.core.interfaces.Searchable;
import com.epam.model.bean.Artist;
import com.epam.model.bean.Genre;
import com.epam.model.exception.EmptyListException;

public class GenreSearchService implements Searchable<Genre> {
	private ArtistDAOImpl artistDAOImpl;
	private GenreDAOImpl genreDAOImpl;
	private String identifier;

	public GenreSearchService() {
		artistDAOImpl = new ArtistDAOImpl();
		genreDAOImpl = new GenreDAOImpl();
	}

	@Deprecated
	@Override
	public List<Genre> search(int id) throws EmptyListException,
			NullPointerException {
		return null;
	}

	@Override
	public List<Genre> search(String parameter) throws EmptyListException,
			NullPointerException {
		List<Genre> genreList = null;
			genreList=genreDAOImpl.getGanreByName(parameter);
			if (genreList == null) {
				throw new NullPointerException();
			} else if (genreList.isEmpty()) {
				throw new EmptyListException();
			}
		return genreList;		
	}

	@Override
	public String keySearch() {
		return "Genre";
	}

	public List<Artist> searchArtistByGenre(String parameter)
			throws EmptyListException, NullPointerException {
		List<Artist> artists = artistDAOImpl.getAllArtistByGenreName(parameter);
		if (artists == null) {
			throw new NullPointerException();
		} else if (artists.isEmpty()) {
			throw new EmptyListException();
		}
		return artists;
	}

	public List<Artist> searchArtistByGenreId(int id)
			throws EmptyListException, NullPointerException {
		List<Artist> artists = artistDAOImpl.getAllArtistsByGenreId(id);
		if (artists == null) {
			throw new NullPointerException();
		} else if (artists.isEmpty()) {
			throw new EmptyListException();
		}
		return artists;
	}

	public List<Genre> getAllGenres() throws EmptyListException,
			NullPointerException {
		List<Genre> genres = genreDAOImpl.getAll();
		if (genres == null) {
			throw new NullPointerException();
		} else if (genres.isEmpty()) {
			throw new EmptyListException();
		}
		return genres;

	}

	@Override
	public void setIdentifier(String identifier) {
		this.identifier = identifier;

	}

}
