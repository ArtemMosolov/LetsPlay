package com.epam.controller.service.song;

import java.util.HashMap;
import java.util.List;

import com.epam.controller.dao.genre.GenreDAOImpl;
import com.epam.model.bean.Genre;

public class SongGenreService {
	private HashMap<String, Integer> genreMap = new HashMap<String, Integer>();
	private static SongGenreService instance;
	
	private SongGenreService(){
		
	}
	
	public static SongGenreService getSongGenreService(){
		if (instance == null){
			instance=new SongGenreService();
		}
		return instance;
	}
	
	public HashMap<String, Integer> getAllGenres(){
		GenreDAOImpl genreDao = new GenreDAOImpl();
		List<Genre> genreModels = genreDao.getAll();
		for(Genre genre:genreModels){
			genreMap.put(genre.getNameGenre(), genre.getIdGenre());
		}
		return genreMap;
	}
}
