package com.epam.model.bean.search;

import javax.xml.bind.annotation.XmlRegistry;

import com.epam.model.bean.search.Search.Item;

@XmlRegistry
public class ObjectFactory {

	public ObjectFactory() {
	}

	public Item createSearchItem() {
		return new Item();
	}

	public Search createSearch() {
		return new Search();
	}

}
