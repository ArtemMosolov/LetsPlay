package com.epam.controller.service.user.playlist;

import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.core.interfaces.DeleteData;
import com.epam.model.bean.UserPlayList;
import com.epam.model.exception.FileNotDeleteException;

public class PlaylistDeleteService implements DeleteData<UserPlayList> {

	@Override
	public void deleteData(BaseDAOInterfase<UserPlayList> baseDAOInterfase,
			int id) throws SQLException, FileNotDeleteException {
		
		baseDAOInterfase.delete(id);
	}

}
