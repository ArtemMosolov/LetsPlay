package com.epam.controller.service.album;

import java.util.List;

import com.epam.controller.dao.album.AlbumDAOImpl;
import com.epam.core.interfaces.Searchable;
import com.epam.model.bean.Album;
import com.epam.model.exception.EmptyListException;

public class AblumSearchService implements Searchable<Album> {
	private AlbumDAOImpl albumDAOImpl;
	private String identifier;

	public AblumSearchService() {
		albumDAOImpl = new AlbumDAOImpl();
	}

	@Override
	public List<Album> search(int id) throws EmptyListException,
			NullPointerException {
		List<Album> albums = albumDAOImpl.getAlbumsByArtistId(id);
		if (albums == null) {
			throw new NullPointerException();
		} else if (albums.isEmpty()) {
			throw new EmptyListException();
		}
		return albums;
	}

	@Override
	public List<Album> search(String parameter) throws EmptyListException,
			NullPointerException {
		List<Album> ablums = null;
		if (identifier == null) {
			ablums = albumDAOImpl.getAlbumByName(parameter);

			if (ablums == null) {
				throw new NullPointerException();
			} else if (ablums.isEmpty()) {
				throw new EmptyListException();
			}
		} else if (identifier.equalsIgnoreCase("Artist")) {
			ablums = albumDAOImpl.getAlbumsByArtistName(parameter);
		}

		return ablums;
	}

	@Override
	public String keySearch() {
		return "Album";
	}

	@Override
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<Album> getAllAlbum() throws EmptyListException,
			NullPointerException {
		List<Album> albums = null;
		if (identifier == null) {
			albums = albumDAOImpl.getAll();
			if (albums == null) {
				throw new NullPointerException();
			} else if (albums.isEmpty()) {
				throw new EmptyListException();
			}
		}
		return albums;
	}

	public List<Album> getPopularAlbum() throws EmptyListException,
			NullPointerException {
		List<Album> popularAlbums = albumDAOImpl.getPopularAlbums();
		if (popularAlbums == null) {
			throw new NullPointerException();
		} else if (popularAlbums.isEmpty()) {
			throw new EmptyListException();
		}
		return popularAlbums;
	}

	public List<Album> getAlbumsByIdArtist(int id) throws EmptyListException,
			NullPointerException {
		List<Album> albums = albumDAOImpl.getAlbumsByArtistId(id);
		if (albums == null) {
			throw new NullPointerException();
		} else if (albums.isEmpty()) {
			throw new EmptyListException();
		}
		return albums;
	}

	public Album getAlbumByIdAlbum(int id) throws NullPointerException {
		Album album = albumDAOImpl.readId(id);
		if (album == null) {
			throw new NullPointerException();
		}
		return album;
	}

}
