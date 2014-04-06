package com.epam.core.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.epam.model.bean.search.Search;

public class SearchPriorityParser {

	private URI uri = null;

	private static Logger logger = Logger.getLogger(SearchPriorityParser.class);

	public SearchPriorityParser() {
		URL url = SearchPriorityParser.class.getClassLoader().getResource(
				"SearchPriority.xml");
		try {
			uri = url.toURI();
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
		}
	}

	public Search getSearchPriority() {
		File file = new File(uri);
		Search search = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Search.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			search = (Search) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			logger.error(e.getMessage());
		}

		return search;
	}
}
