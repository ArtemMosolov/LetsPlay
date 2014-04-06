package com.epam.controller.dao.artist;

import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;

public interface ArtistDAO<T> extends BaseDAOInterfase<T> {

	List<T> getArtistByName(String name);

	List<T> getAll();

	List<T> getArtistByGenre(int[] id);

	T getArtistByAlbum(int id);

	T getArtistByIdSong(int id);

	List<T> getAtristByNameSong(String name);

	List<T> getArtistByNameAlbum(String name);
	
	List<T> getPopularArtists();
}