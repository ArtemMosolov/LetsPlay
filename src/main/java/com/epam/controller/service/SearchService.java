package com.epam.controller.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.epam.core.factory.FactorySearchService;
import com.epam.core.interfaces.Searchable;
import com.epam.model.exception.EmptyListException;

public class SearchService {

	private HashMap<String, List<?>> searchResult;
	private FactorySearchService factorySearchService;
	private Iterator<String> iterator;
	private String identificator;

	public SearchService() {
		searchResult = new HashMap<>();
		factorySearchService = FactorySearchService.getInstance();
		iterator = factorySearchService.getMapPriority().keySet().iterator();
	}

	public HashMap<String, List<?>> getResult() {
		return searchResult;
	}

	private void setResult(String key, List<?> result) {
		searchResult.put(key, result);
	}

	public void search(String name) {
		useServiceSerarch(nextElement(), name);
	}

	private void useServiceSerarch(Searchable<?> searchable, String parameter) {
		try {
			searchable.setIdentifier(null);
			List<?> tmpListResult = null;

			if (identificator != null) {
				searchable.setIdentifier(identificator);
			}
			tmpListResult = searchable.search(parameter);

			if (tmpListResult.size() < 2) {
				setResult(searchable.keySearch(), tmpListResult);
				Searchable<?> tmpSearchable = nextElement();
				identificator = searchable.keySearch();
				useServiceSerarch(tmpSearchable, parameter);
			} else {
				setResult(searchable.keySearch(), tmpListResult);
				useServiceSerarch(nextElement(), parameter);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyListException e) {
			useServiceSerarch(nextElement(), parameter);
		} catch (NoSuchElementException e) {
			return;
		}
	}

	private Searchable<?> nextElement() throws NoSuchElementException {
		if (iterator.hasNext()) {
			return factorySearchService.getSearchables(iterator.next());
		} else {
			throw new NoSuchElementException();
		}
	}
}
