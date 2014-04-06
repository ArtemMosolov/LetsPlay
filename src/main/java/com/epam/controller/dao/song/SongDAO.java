package com.epam.controller.dao.song;

import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;

public interface SongDAO<T> extends BaseDAOInterfase<T> {

	List<T> getSongByName(String name);

	List<T> getAllSongsByAlbumId(int id);

	List<T> getAllSongsByAlbumName(String name);
	
	List<T> getAllSong();
	
	List<T> getPopularSongs();
	
	List<T> getPopularSongsByIdArtist(int id);
}
