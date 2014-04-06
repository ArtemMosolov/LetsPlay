package com.epam.controller.service.user;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.epam.core.util.MD5Encrypter;
import com.epam.core.util.Validator;
import com.epam.model.bean.UserModel;

public class RegistrationService {

	private UserService userService;
	public static Logger logger = Logger.getLogger(RegistrationService.class);
	private HashMap<String, String> errors = new HashMap<>();

	private boolean checkUser(UserModel user) {
		boolean result = true;
		if (!Validator.USER_PASSWORD.validate(user.getPassword())) {
			result = false;
			errors.put("passwordWrong", "ERROR_INVALID_PASSWORD");
		}

		if (!Validator.USER_LOGIN.validate(user.getLogin())) {
			errors.put("loginWrong", "ERROR_INVALID_LOGIN");
			result = false;
		}

		if (!Validator.USER_EMAIL.validate(user.getEmail())) {
			errors.put("emailWrong", "ERROR_INVALID_EMAIL");
			result = false;
		}
		return result;

	}

	private UserModel encryptUser(UserModel user) {
		MD5Encrypter md5 = new MD5Encrypter();
		user.setEmail(user.getEmail());
		user.setPassword(md5.encrypt(user.getPassword()));
		return user;

	}

	public boolean createUser(UserModel user) {
		userService = new UserService();
		boolean result = false;
		if (checkUser(user)) {
			if (userService.createUser(encryptUser(user))) {

				result = true;
			} else
				errors.put("exists", "ERROR_USER_ALREADY_EXISTS");
		}

		return result;

	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}
}
