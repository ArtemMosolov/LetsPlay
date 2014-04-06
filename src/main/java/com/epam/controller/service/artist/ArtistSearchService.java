package com.epam.controller.service.artist;

import java.util.ArrayList;
import java.util.List;

import com.epam.controller.dao.artist.ArtistDAOImpl;
import com.epam.core.interfaces.Searchable;
import com.epam.model.bean.Artist;
import com.epam.model.exception.EmptyListException;

public class ArtistSearchService implements Searchable<Artist> {
	
	private ArtistDAOImpl artistDAOImpl;
	
	private String identifier;
	
	public ArtistSearchService() {
		artistDAOImpl = new ArtistDAOImpl();
	}

	public ArtistDAOImpl getArtistDAOImpl() {
		return artistDAOImpl;
	}

	public void setArtistDAOImpl(ArtistDAOImpl artistDAOImpl) {
		this.artistDAOImpl = artistDAOImpl;
	}
	public Artist searh(int id){
		return artistDAOImpl.readId(id);
	}

	@Override
	public List<Artist> search(String parameter) throws EmptyListException,
			NullPointerException {
		List<Artist> artists=null;
		 artists= artistDAOImpl.getArtistByName(parameter);
		if (artists == null) {
			throw new NullPointerException();
		} else if (artists.isEmpty()) {
			throw new EmptyListException();
		}

		return artists;
	}
	@Override
	public String keySearch() {
		return "Artist";
	}

	public List<Artist> searchBySong(String parameter)
			throws EmptyListException, NullPointerException {
		List<Artist> artists = artistDAOImpl.getAtristByNameSong(parameter);
		return artists;
	}

	public List<Artist> searchByAlbum(String parameter)
			throws EmptyListException, NullPointerException {
		List<Artist> artists = artistDAOImpl.getArtistByNameAlbum(parameter);

		return artists;
	}

	public List<Artist> searchByGenreName(String parameter)
			throws EmptyListException, NullPointerException {
		List<Artist> artists = artistDAOImpl.getAllArtistByGenreName(parameter);
		return artists;
	}

	public List<Artist> getAllArtist() throws EmptyListException,
			NullPointerException {
		ArrayList<Artist> arrayList = (ArrayList<Artist>) artistDAOImpl
				.getAll();
		if (arrayList == null) {
			throw new NullPointerException();
		} else if (arrayList.isEmpty()) {
			throw new EmptyListException();
		}
		return arrayList;
	}

	@Override
	public void setIdentifier(String identifier) {
		this.identifier=identifier;		
	}

	@Override
	public List<Artist> search(int id) throws EmptyListException,
			NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Artist> getPopularArtists() throws EmptyListException,NullPointerException {
		List<Artist> popularArtists = artistDAOImpl.getPopularArtists();
		if(popularArtists == null) {
			throw new NullPointerException();
		} else if(popularArtists.isEmpty()) {
			throw new EmptyListException();
		}
		return popularArtists;
	}
	
	public Artist getArtist(int id) throws NullPointerException {
		Artist artist = artistDAOImpl.readId(id);
		if(artist == null) {
			throw new NullPointerException();
		}
		return artist;
	}

}
