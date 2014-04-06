package com.epam.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Validator {
	
	USER_LOGIN("[A-Za-z0-9]{4,16}$"), 
	USER_EMAIL("^([0-9a-zA-Z]+[-._+&amp;])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$"), 
	USER_PASSWORD("^[0-9A-Za-z]{4,32}$"), 
	MD5_CHECKSUM("[a-fA-F0-9]{32}"),
	EMPTY_FIELD("^$"),
	IMAGE_VALID("([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)"), 
	MUSIC(".*\\.(mp3|mp4|aac|wma)"),
	USER_FIRST_NAME("[A-Z][a-z]*"),
	USER_LAST_NAME("[A-Z][a-z]*"),
	USER_DATE_OF_BIRTH("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)"),
	USER_COUNTRY("[A-Z][a-z\\s]+[A-Z][a-z]*"),
	USER_CITY("[A-Z][a-z\\s-]+[A-Z][a-z]*"),
	USER_ABOUT("[A-Za-z\\s.?!:;()]*"),
	NAME("[A-Za-z0-9\\s]*"),
	TITLE("A-Za-z\\s]*"),
	ABOUT("[A-Za-z0-9\\s.]*");
	
	private String regex;

	private Validator(String regex) {
		this.regex = regex;
	}

	public boolean validate(String string) {
		boolean res = false;

		if (string != null) {
			Pattern pattern = Pattern.compile(this.regex,Pattern.UNICODE_CHARACTER_CLASS);
			Matcher matcher = pattern.matcher(string);
			res = matcher.matches();
		}

		return res;
	}

}
