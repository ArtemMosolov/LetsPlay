package com.epam.core.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperty {
	public String getFolderName() {
		String result = null;
		Properties prop = new Properties();
		try (InputStream input = ReadProperty.class.getClassLoader()
				.getResourceAsStream("config.properties")) {
			prop.load(input);
			result = prop.getProperty("file.dir");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
