package com.epam.controller.service.song;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.song.SongDAOImpl;
import com.epam.controller.service.BaseServise;
import com.epam.controller.service.album.AblumSearchService;
import com.epam.core.interfaces.DeleteData;
import com.epam.core.interfaces.Searchable;
import com.epam.model.bean.Album;
import com.epam.model.bean.Song;
import com.epam.model.exception.EmptyListException;

public class SongService extends BaseServise<Song> {

	private SongDeployService songDeployService;

	private SongSearchService songSearchService;

	private SongDAOImpl songDAOImpl;

	public SongService() {
		this(new SongDeleteService(), new SongDAOImpl());
		songDAOImpl = new SongDAOImpl();
	}

	public SongService(DeleteData<Song> deleteService,
			BaseDAOInterfase<Song> baseDAOInterfasee) {
		super(deleteService, baseDAOInterfasee);
	}

	@Override
	public int deploy(Song model) {
		songDeployService = new SongDeployService();
		int result = 0;
		if (model.getIdSong() == null || model.getIdSong() == 0) {
			try {
				result=songDeployService.createRecord(model);
			} catch (SQLException e) {
				setError(getAddError());
				e.printStackTrace();
			}
		} else {
			try {
				songDeployService.updateRecord(model);
			} catch (SQLException e) {
				setError(getUpdateError());
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<Song> getAllModelByTable(int idParent) {
		List<Song> songs = null;
		songSearchService = new SongSearchService();
		try {
			songs = songSearchService.search(idParent);
		} catch (NullPointerException e) {
			setError(e.getMessage());
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return songs;
	}

	@Override
	public List<Song> getAllModelByTable() {
		List<Song> songs = new ArrayList<Song>();
		songSearchService = new SongSearchService();
		try {
			songs = songSearchService.getAllSong();
		} catch (NullPointerException e) {
			setError(e.getMessage());
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return songs;
	}
	public List<Song> search(String parametr, String identifier){
		List<Song> list = null;
		Searchable<Song> searchable = new SongSearchService();
		searchable.setIdentifier(identifier);
		try {
			list = searchable.search(parametr);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError("empty list");
		}
		return list;
	}
	
	public List<Song> getPopularSongs() {
		songSearchService = new SongSearchService();
		List<Song> popularSongs = null;
		try {
			popularSongs = songSearchService.getPopularSong();
		} catch (NullPointerException e) {
			setError("NULL POINT EXCEPTION");
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return popularSongs;
	}
	
	public List<Song> getPopularSongsByIdArtist(int id) {
		songSearchService = new SongSearchService();
		List<Song> popularSongs = null;
		try {
			popularSongs = songSearchService.getPopularSongByIdArtist(id);
		} catch (NullPointerException e) {
			setError("NULL POINT EXCEPTION");
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return popularSongs;
	}
	
	public List<Song> getPopularSongsByIdAlbum(int id) {
		songSearchService = new SongSearchService();
		List<Song> popularSongs = null;
		try {
			popularSongs = songSearchService.getPopularSongByIdAlbum(id);
		} catch (NullPointerException e) {
			setError("NULL POINT EXCEPTION");
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return popularSongs;
	}
	
}
