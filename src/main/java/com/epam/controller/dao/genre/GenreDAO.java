package com.epam.controller.dao.genre;

import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;

public interface GenreDAO<T> extends BaseDAOInterfase<T> {

	public List<T> getAll();
	List<T> getGanreByName(String name);
	
}