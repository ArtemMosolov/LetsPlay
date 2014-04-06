package com.epam.controller.service.song;

import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.TransactionSongDAO;
import com.epam.controller.dao.song.SongDAOImpl;
import com.epam.core.BaseDeploy;
import com.epam.core.interfaces.TransactionalDAO;
import com.epam.model.bean.Song;

public class SongDeployService extends BaseDeploy<Song> {

	public SongDeployService() {
		this(new TransactionSongDAO(),new SongDAOImpl());
	}

	public SongDeployService(TransactionalDAO transactionalDAO,
			SongDAOImpl songDaoImpl) {
		super(transactionalDAO, songDaoImpl);
	}

	@Override
	public int createRecord(Song model) throws SQLException {
		int result = 0;
		if (model.getGenre().getIdGenre() != null) {
			result = this.create(model);
			if (result == 0) {
				throw new SQLException();
			}
		} else {
			return result;
		}

		return result;
	}

	@Override
	public void updateRecord(Song model) throws SQLException {
		if (model.getGenre().getIdGenre() != null) {
			this.update(model);
		}
	}

}
