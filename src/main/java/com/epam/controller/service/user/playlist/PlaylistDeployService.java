package com.epam.controller.service.user.playlist;

import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.core.BaseDeploy;
import com.epam.model.bean.UserPlayList;

public class PlaylistDeployService extends BaseDeploy<UserPlayList> {

	public PlaylistDeployService(BaseDAOInterfase<UserPlayList> baseDAOInterfase) {
		super(baseDAOInterfase);
	}

	@Override
	public int createRecord(UserPlayList model) throws SQLException {
		int result = this.createOne(model);
		if (result == 0) {
			throw new SQLException();
		}
		return result;
	}

	@Override
	public void updateRecord(UserPlayList model) throws SQLException {
		if (!this.updateOne(model)) {
			throw new SQLException();
		}
	}

}
