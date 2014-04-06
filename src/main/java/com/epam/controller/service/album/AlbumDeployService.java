package com.epam.controller.service.album;

import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.TransactionAlbumDAO;
import com.epam.controller.dao.album.AlbumDAOImpl;
import com.epam.core.BaseDeploy;
import com.epam.core.interfaces.TransactionalDAO;
import com.epam.model.bean.Album;

public class AlbumDeployService extends BaseDeploy<Album> {
	
	public AlbumDeployService() {
		this(new TransactionAlbumDAO(),new AlbumDAOImpl());
	}
	
	public AlbumDeployService(BaseDAOInterfase<Album> baseDAOInterfase) {
		super(baseDAOInterfase);
	}
	public AlbumDeployService(TransactionalDAO transactionalDAO,
			BaseDAOInterfase<Album> baseDAOInterfase) {
		super(transactionalDAO, baseDAOInterfase);
	}
	@Override
	public int createRecord(Album model) throws SQLException {
		int result = 0;
		if (model.getAlbumDescription() != null) {
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
	public void updateRecord(Album model) throws SQLException {
		if (model.getAlbumDescription() != null) {
			this.update(model);
		}else{
			if(!this.updateOne(model)){
				throw new SQLException();
			}
		}		
	}

}
