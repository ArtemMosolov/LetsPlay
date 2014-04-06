package com.epam.model.language;

import java.util.ArrayList;
import java.util.List;

public enum Language {
	UKRAINIAN("uk_UA", "/resourse/image/ico/UA.png", "UKRAINIAN"), RUSSIAN(
			"ru_RU", "/resourse/image/ico/RU.png", "RUSSIAN"), ENGLISH("en_US",
			"/resourse/image/ico/US.png", "ENGLISH");

	private static final List<Language> languages;
	static {
		languages = new ArrayList<Language>();
		languages.add(UKRAINIAN);
		languages.add(RUSSIAN);
		languages.add(ENGLISH);

	}

	private String locale;
	private String path;
	private String name;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Language(String locale, String path, String name) {
		this.locale = locale;
		this.path = path;
		this.setName(name);
	}

	public static List<Language> getLanguages() {
		return languages;
	}

	public static Language findByLocale(String locale) {
		for (Language language : languages) {
			if (language.getLocale().equalsIgnoreCase(locale)) {
				return language;
			}
		}
		return null;
	}

}
