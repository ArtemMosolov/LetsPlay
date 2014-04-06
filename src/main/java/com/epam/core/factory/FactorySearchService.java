package com.epam.core.factory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.epam.core.interfaces.Searchable;
import com.epam.model.bean.search.Search;
import com.epam.model.bean.search.Search.Item;

public class FactorySearchService {
	private static FactorySearchService INSTANCE = null;
	private HashMap<String, Searchable<?>> searchables;
	private Search searchs;
	private HashMap<String, Integer> mapPriority;
	private Map<String, Integer> priorityMap;

	public Search getSearchs() {
		return searchs;
	}

	public void setSearchs(Search searchs) {
		this.searchs = searchs;
	}

	private FactorySearchService() {
		searchables = new HashMap<>();
		mapPriority = new HashMap<>();
	}

	public synchronized static FactorySearchService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FactorySearchService();
		}
		return INSTANCE;
	}

	public Searchable<?> getSearchables(String key) {
		return this.searchables.get(key);
	}

	public void setSearchables(Searchable<?> searchable) {
		searchables.put(searchable.keySearch(), searchable);
	}

	public void parserPriority() {
		for (Item item : searchs.getItem()) {
			mapPriority.put(item.getName(), item.getPriority());
		}

		ValueComparator valueComparator = new ValueComparator(mapPriority);
		priorityMap = new TreeMap<String, Integer>(valueComparator);
		priorityMap.putAll(mapPriority);
	}

	public Map<String, Integer> getMapPriority() {
		return priorityMap;
	}

	public Searchable<?> nextItem() {
		return null;
	}

	class ValueComparator implements Comparator<String> {
		Map<String, Integer> base;

		public ValueComparator(Map<String, Integer> base) {
			this.base = base;
		}

		public int compare(String o1, String o2) {
			if (base.get(o1) >= base.get(o2)) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}