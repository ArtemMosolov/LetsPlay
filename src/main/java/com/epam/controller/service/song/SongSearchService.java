package com.epam.controller.service.song;

import java.util.ArrayList;
import java.util.List;

import com.epam.controller.dao.song.SongDAOImpl;
import com.epam.core.interfaces.Searchable;
import com.epam.model.bean.Song;
import com.epam.model.exception.EmptyListException;

public class SongSearchService implements Searchable<Song> {

	private SongDAOImpl songDAOImpl;
	private String identifier;

	public SongSearchService() {
		songDAOImpl = new SongDAOImpl();

	}

	public void setSongDAOImpl(SongDAOImpl songDAOImpl) {
		this.songDAOImpl = songDAOImpl;
	}

	@Override
	public List<Song> search(int id) throws EmptyListException,
			NullPointerException {
		List<Song> songs = songDAOImpl.getAllSongsByAlbumId(id);
		if (songs == null) {
			throw new NullPointerException();
		} else if (songs.isEmpty()) {
			throw new EmptyListException();
		}
		return songs;
	}

	@Override
	public List<Song> search(String parameter) throws EmptyListException,
			NullPointerException {
		ArrayList<Song> arrayList = null;
		if (identifier == null) {
			arrayList = (ArrayList<Song>) songDAOImpl.getSongByName(parameter);
			if (arrayList == null) {
				throw new NullPointerException();
			} else if (arrayList.isEmpty()) {
				throw new EmptyListException();
			}
		} else if (identifier.equalsIgnoreCase("Artist")) {
			arrayList = (ArrayList<Song>) songDAOImpl
					.getSongByArtistName(parameter);

		} else if (identifier.equalsIgnoreCase("Album")) {
			arrayList = (ArrayList<Song>) songDAOImpl
					.getAllSongsByAlbumName(parameter);

		}
		return arrayList;
	}

	@Override
	public String keySearch() {
		return "Song";
	}

	@Override
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<Song> getAllSong() throws EmptyListException,
			NullPointerException {
		List<Song> arrayList = songDAOImpl.getAllSong();
		if (arrayList == null) {
			throw new NullPointerException();
		} else if (arrayList.isEmpty()) {
			throw new EmptyListException();
		}
		return arrayList;
	}

	public List<Song> getPopularSong() throws EmptyListException,
			NullPointerException {
		List<Song> popularSongs = songDAOImpl.getPopularSongs();
		if (popularSongs == null) {
			throw new NullPointerException();
		} else if (popularSongs.isEmpty()) {
			throw new EmptyListException();
		}
		return popularSongs;
	}

	public List<Song> getPopularSongByIdArtist(int id)
			throws EmptyListException, NullPointerException {
		List<Song> popularSongs = songDAOImpl.getPopularSongsByIdArtist(id);
		if (popularSongs == null) {
			throw new NullPointerException();
		} else if (popularSongs.isEmpty()) {
			throw new EmptyListException();
		}
		return popularSongs;
	}

	public List<Song> getPopularSongByIdAlbum(int id)
			throws EmptyListException, NullPointerException {
		List<Song> popularSongs = songDAOImpl.getAllSongsByAlbumId(id);
		if (popularSongs == null) {
			throw new NullPointerException();
		} else if (popularSongs.isEmpty()) {
			throw new EmptyListException();
		}
		return popularSongs;
	}

}
