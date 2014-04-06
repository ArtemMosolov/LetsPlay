package com.epam.core.interfaces;

import java.io.FileNotFoundException;

import com.epam.model.exception.FileNotDeleteException;

public interface DeleteSource<T> extends DeleteData<T> {
	void deleteSource(String path) throws FileNotDeleteException,
			FileNotFoundException;
}
