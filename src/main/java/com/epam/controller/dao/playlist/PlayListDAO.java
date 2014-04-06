package com.epam.controller.dao.playlist;

import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;

public interface PlayListDAO<T> extends BaseDAOInterfase<T> {
	public void delete(int playlistId, int songId) throws SQLException;
}