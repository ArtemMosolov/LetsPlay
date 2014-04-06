package com.epam.controller.dao;

import java.sql.SQLException;

public interface BaseDAOInterfase<T> {
	int create(T model);

	T readId(int id);

	void delete(int id) throws SQLException;

	int update(T model);
}
