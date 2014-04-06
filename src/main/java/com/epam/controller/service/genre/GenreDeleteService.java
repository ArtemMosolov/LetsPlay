package com.epam.controller.service.genre;

import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.core.interfaces.DeleteData;
import com.epam.model.bean.Genre;
import com.epam.model.exception.FileNotDeleteException;

public class GenreDeleteService implements DeleteData<Genre> {

	@Override
	public void deleteData(BaseDAOInterfase<Genre> baseDAOInterfase, int id)
			throws SQLException, FileNotDeleteException {
		baseDAOInterfase.delete(id);
	}
}
