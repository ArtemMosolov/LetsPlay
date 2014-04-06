package com.epam.core.interfaces;

import java.sql.SQLException;

public interface DeployData<T> {

	int createRecord(T model)
			throws SQLException;

	void updateRecord(T model)
			throws SQLException;
}
