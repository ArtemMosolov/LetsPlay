package com.epam.controller.service.artist;

import java.sql.SQLException;

import com.epam.controller.dao.TransactionArtistDAO;
import com.epam.controller.dao.artist.ArtistDAOImpl;
import com.epam.core.BaseDeploy;
import com.epam.core.interfaces.TransactionalDAO;
import com.epam.model.bean.Artist;

public class ArtistDeployService extends BaseDeploy<Artist> {
	
	public ArtistDeployService() {
		this(new TransactionArtistDAO(),new ArtistDAOImpl());
	}

	public ArtistDeployService(TransactionalDAO transactionalDAO,ArtistDAOImpl artistDAOImpl) {
		super(transactionalDAO,artistDAOImpl);
	}

	@Override
	public int createRecord(Artist model) throws SQLException {
		int result = 0;
		if (model.getArtistDescription() != null) {
			result = this.create(model);
			if(result == 0){
				throw new SQLException();
			}
		}else{
			result = this.createOne(model);
			if(result == 0){
				throw new SQLException();
			}
		}
		return result;
	}

	@Override
	public void updateRecord(Artist model) throws SQLException {
		if (model.getArtistDescription() != null) {
			this.update(model);
		}else{
			if(!this.updateOne(model)){
				throw new SQLException();
			}
		}
	}

}
