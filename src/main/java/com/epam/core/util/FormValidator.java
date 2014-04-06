package com.epam.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class FormValidator {

	private static HashMap<String, String> formField;

	static {
		formField = new HashMap<String, String>();
		formField.put("login", "[A-Za-z0-9]{4,16}$");
		formField
				.put("email",
						"^([0-9a-zA-Z]+[-._+&amp;])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		formField.put("password", "^[0-9A-Za-z]{4,32}$");
		formField.put("repeatPassword", "^[0-9A-Za-z]{4,32}$");

		formField.put("image", "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)");
		formField.put("music", "([^\\s]+(\\.(?i)(mp3|mp4|aac|wma))$)");

		formField.put("md5", "[a-fA-F0-9]{32}");
		formField.put("empty", "^$");
		formField.put("image", "[A-Za-z0-9]{4,16}$");
		formField.put("music", "[A-Za-z0-9]{4,16}$");

		formField.put("firstName", "[A-Z][a-z]*");
		formField.put("lastName", "[A-Z][a-z]*");
		formField.put("date",
				"(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
		formField.put("country", "[A-Z][a-z\\s]+[A-Z][a-z]*");
		formField.put("city", "[A-Z][a-z\\s-]+[A-Z][a-z]*");
		formField.put("userAbout", "[A-Za-z\\s.?!:;()]*");
		formField.put("songName", ".*");

		formField.put("artistName", ".*$");
		formField.put("albumName", ".*$");
		formField.put("albumTitle", ".*$");
		formField.put("title", ".*$");
		formField.put("about", ".*$");
		formField.put("artistId", "\\d+");
		formField.put("albumid", "\\d+");
		formField.put("idImage", "\\d+");

	}

	public static boolean validateField(String fieldName, String fieldValue) {
		boolean result = false;
		if (fieldName != null && fieldValue != null) {
			String existField = formField.get(fieldName);
			if (existField == null) {
				result = false;
			} else {
				Pattern pattern = Pattern.compile(formField.get(fieldName),
						Pattern.UNICODE_CHARACTER_CLASS);
				Matcher matcher = pattern.matcher(fieldValue);
				result = matcher.matches();
			}
		}
		return result;
	}

	public static boolean validateField(HttpServletRequest request) {
		Map<String, String[]> parametrs = request.getParameterMap();
		boolean result = true;
		for (String fieldName : parametrs.keySet()) {
			if (!validateField(fieldName, parametrs.get(fieldName)[0])) {
				result = false;
			}
		}
		return result;
	}

}
