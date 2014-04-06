package com.epam.controller.dao.album;

import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;

public interface AlbumDAO<T> extends BaseDAOInterfase<T> {

	List<T> getAlbumByName(String name);

	List<T> getAlbumsByArtistId(int id);
	
	List<T> getAlbumsByArtistName(String name);
	
	List<T> getAll();
	
	List<T> getPopularAlbums();
}