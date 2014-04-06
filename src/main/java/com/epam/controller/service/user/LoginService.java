package com.epam.controller.service.user;

import com.epam.core.util.MD5Encrypter;
import com.epam.core.util.Validator;
import com.epam.model.bean.UserModel;

public class LoginService {

	private UserService userService = new UserService();

	private MD5Encrypter md5 = new MD5Encrypter();

	private UserModel loginEmail(String email, String password, boolean isAdmin) {
		return userService.getUserEmail(email, md5.encrypt(password), isAdmin);
	}

	private UserModel loginName(String login, String password, boolean isAdmin) {
		return userService.getUserLogin(login, md5.encrypt(password), isAdmin);
	}

	private boolean checkParam(String password) {
		return Validator.USER_PASSWORD.validate(password);
	}

	public UserModel brainLogin(String name, String password, boolean isAdmin) {
		UserModel model = null;
		if (checkParam(password)) {
			if (Validator.USER_EMAIL.validate(name)) {
				model = loginEmail(name, password, isAdmin);
			} else if (Validator.USER_LOGIN.validate(name)) {
				model = loginName(name, password, isAdmin);
			}
		}
		return model;
	}
}
