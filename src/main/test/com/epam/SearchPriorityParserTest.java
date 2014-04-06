package com.epam;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.core.util.SearchPriorityParser;

public class SearchPriorityParserTest {

	@Test
	public void testReference() {
		SearchPriorityParser searchPriorityParser = new SearchPriorityParser();
		assertNotNull(searchPriorityParser.getSearchPriority());
	}

	@Test
	public void testListSize() {
		SearchPriorityParser searchPriorityParser = new SearchPriorityParser();
		assertEquals(4, searchPriorityParser.getSearchPriority().getItem()
				.size());
	}

}
