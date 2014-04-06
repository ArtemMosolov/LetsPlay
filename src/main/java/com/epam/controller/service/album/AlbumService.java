package com.epam.controller.service.album;

import java.sql.SQLException;
import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.album.AlbumDAOImpl;
import com.epam.controller.service.BaseServise;
import com.epam.core.interfaces.DeleteData;
import com.epam.core.interfaces.Searchable;
import com.epam.model.bean.Album;
import com.epam.model.exception.EmptyListException;

public class AlbumService extends BaseServise<Album> {

	private AblumSearchService albumSearchService;

	private AlbumDeployService albumDeployService;

	public AlbumService() {
		this(new AlbumDeleteService(), new AlbumDAOImpl());
	}

	protected AlbumService(DeleteData<Album> deleteService,
			BaseDAOInterfase<Album> baseDAOInterfase) {
		super(deleteService, baseDAOInterfase);
	}

	@Override
	public int deploy(Album model) {
		albumDeployService = new AlbumDeployService();
		int result = 0;
		if (model != null) {
			if (model.getIdAlbum() == 0) {
				try {
					result = albumDeployService.createRecord(model);
				} catch (SQLException e) {
					setError(getAddError());
					e.printStackTrace();
				}
			} else {
				try {
					albumDeployService.updateRecord(model);
				} catch (SQLException e) {
					setError(getUpdateError());
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public List<Album> getAllModelByTable() {
		albumSearchService = new AblumSearchService();
		List<Album> albums = null;
		try {
			albums = albumSearchService.getAllAlbum();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return albums;
	}

	@Override
	public List<Album> getAllModelByTable(int idParent) {
		albumSearchService = new AblumSearchService();
		List<Album> albums = null;
		try {
			albums = albumSearchService.search(idParent);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return albums;
	}

	public List<Album> search(String parametr, String identifier) {
		List<Album> albums = null;
		Searchable<Album> searchable = new AblumSearchService();
		searchable.setIdentifier(identifier);
		try {
			albums = searchable.search(parametr);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError("empty list");
		}
		return albums;
	}

	public List<Album> getPopularAlbums() {
		albumSearchService = new AblumSearchService();
		List<Album> popularAlbums = null;
		try {
			popularAlbums = albumSearchService.getPopularAlbum();
		} catch (NullPointerException e) {
			setError("NULL POINT EXCEPTION");
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return popularAlbums;
	}
	
	public List<Album> getAlbumsByIdArtist(int id) {
		albumSearchService = new AblumSearchService();
		List<Album> albums = null;
		try {
			albums = albumSearchService.getAlbumsByIdArtist(id);
		} catch (NullPointerException e) {
			setError("NULL POINT EXCEPTION");
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return albums;
	}
	
	public Album getAlbumByIdAlbum(int id) {
		albumSearchService = new AblumSearchService();
		Album album = null;
		try {
			album = albumSearchService.getAlbumByIdAlbum(id);
		} catch (NullPointerException e) {
			setError("NULL POINT EXCEPTION");
		} 
		return album;
	}
}
