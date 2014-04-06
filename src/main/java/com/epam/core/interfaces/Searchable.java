package com.epam.core.interfaces;

import java.util.List;

import com.epam.model.exception.EmptyListException;

public interface Searchable<T> {

	List<T> search(int id) throws EmptyListException, NullPointerException;

	List<T> search(String parameter) throws EmptyListException,
			NullPointerException;

	String keySearch();
	
	void setIdentifier(String identifier);
}
