package com.epam.controller.service.genre;

import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.core.BaseDeploy;
import com.epam.core.interfaces.DeployData;
import com.epam.core.interfaces.TransactionalDAO;
import com.epam.model.bean.Genre;

public class GenreDeployService extends BaseDeploy<Genre>{

	public GenreDeployService(BaseDAOInterfase<Genre> baseDAOInterfase) {
		super(baseDAOInterfase);
	}
	@Override
	public int createRecord(Genre model) throws SQLException {
		int result = this.createOne(model);
		if(result == 0){
			throw new SQLException();
		}
		return result;
	}

	@Override
	public void updateRecord(Genre model) throws SQLException {
		if(!this.updateOne(model)){
			throw new SQLException();
		}
	}
}
