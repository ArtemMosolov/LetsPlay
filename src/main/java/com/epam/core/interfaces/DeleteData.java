package com.epam.core.interfaces;

import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.model.exception.FileNotDeleteException;

public interface DeleteData<T> {
	void deleteData(BaseDAOInterfase<T> baseDAOInterfase, int id)
			throws SQLException, FileNotDeleteException;
}
