package com.epam.controller.service.artist;

import java.sql.SQLException;
import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.artist.ArtistDAOImpl;
import com.epam.controller.service.BaseServise;
import com.epam.controller.service.album.AblumSearchService;
import com.epam.core.BaseDeploy;
import com.epam.core.interfaces.DeleteData;
import com.epam.core.interfaces.Searchable;
import com.epam.model.bean.Album;
import com.epam.model.bean.Artist;
import com.epam.model.exception.EmptyListException;

public class ArtistService extends BaseServise<Artist> {

	private BaseDeploy<Artist> artistDeployService;
	private ArtistSearchService artistSearchService;

	public ArtistService() {
		this(new ArtistDeleteService(), new ArtistDAOImpl());
	}

	protected ArtistService(DeleteData<Artist> deleteService,
			BaseDAOInterfase<Artist> baseDAOInterfase) {
		super(deleteService, baseDAOInterfase);
	}

	@Override
	public int deploy(Artist model) {
		artistDeployService = new ArtistDeployService();
		int result = 0;
		if (model != null) {
			if (model.getIdArtist() == 0) {
				try {
					result = artistDeployService.createRecord(model);
				} catch (SQLException e) {
					setError(getAddError());
					e.printStackTrace();
				}
			} else {
				try {
					artistDeployService.updateRecord(model);
				} catch (SQLException e) {
					setError(getUpdateError());
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@Override
	public Artist getModelById(int id) {
		return super.getModelById(id);
	}

	@Override
	public List<Artist> getAllModelByTable() {
		artistSearchService = new ArtistSearchService();
		List<Artist> artists = null;
		try {
			artists = artistSearchService.getAllArtist();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return artists;
	}

	public List<Artist> search(String parametr) {
		Searchable<Artist> searchable = new ArtistSearchService();
		List<Artist> artists = null;
		try {
			artists = searchable.search(parametr);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return artists;
	}

	public List<Artist> getPopularArtist() {
		artistSearchService = new ArtistSearchService();
		List<Artist> popularArtist = null;
		try {
			popularArtist = artistSearchService.getPopularArtists();
		} catch (NullPointerException e) {
			setError("NULL POINT EXCEPTION");
		} catch (EmptyListException e) {
			setError(getEmptyError());
		}
		return popularArtist;
	}
}
