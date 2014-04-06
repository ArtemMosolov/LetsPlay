package com.epam.controller.service.user;

import java.util.Date;

import com.epam.controller.dao.image.ImageDAOImpl;
import com.epam.controller.dao.user.UserDAOImpl;
import com.epam.controller.dao.userinfo.UserInfoDAOImpl;
import com.epam.model.bean.Image;
import com.epam.model.bean.UserInfo;
import com.epam.model.bean.UserModel;

public class UserService {

	private UserDAOImpl userDAOImpl;

	private UserInfoDAOImpl userInfoDAOImpl;
	private ImageDAOImpl imageDAOImpl;

	public UserService() {
		userDAOImpl = new UserDAOImpl();
		userInfoDAOImpl = new UserInfoDAOImpl();
	}

	public UserModel getUserById(int id) {
		return userDAOImpl.readId(id);
	}

	public UserModel getUserByEmail(String email) {
		return userDAOImpl.readEmail(email, false);
	}

	public UserModel getUserEmail(String email, String encrypt, boolean isAdmin) {
		UserModel model = userDAOImpl.readEmail(email, isAdmin);
		if (model != null) {
			if (!model.getPassword().equals(encrypt)) {
				model = null;
			}
		}
		return model;
	}

	public UserModel getUserLogin(String login, String encrypt, boolean isAdmin) {
		UserModel model = userDAOImpl.readLogin(login, isAdmin);
		if (model != null) {
			if (!model.getPassword().equals(encrypt)) {
				model = null;
			}
		}
		return model;
	}

	public boolean createUser(UserModel model) {
		boolean result = false;
		if (userDAOImpl.create(model) != 0) {
			result = true;
		}
		return result;
	}

	public boolean updateUser(UserModel model) {
		boolean result = false;
		if (userDAOImpl.update(model) != 0) {
			result = true;
		}
		return result;
	}

	public UserInfo getUserInfo(int userId) {
		UserInfo model = null;
		if (!checkExistingUserInfo(userId)) {
			UserInfo userInfo = new UserInfo();
			userInfo.setIdUser(userId);
			userInfoDAOImpl.create(userInfo);

		}
		model = userInfoDAOImpl.readId(userId);
		// fromSQLDateToJavaDate(model);
		return model;
	}

	private void fromSQLDateToDateUtil(UserInfo model) {
		Date date = new Date(model.getDateOfBirth().getTime());
		model.setDateOfBirth(date);
	}

	private boolean checkExistingUserInfo(int userId) {
		boolean result = false;
		if (userInfoDAOImpl.readId(userId) != null) {
			result = true;

		}
		return result;

	}

	public boolean createUserInfo(UserInfo userInfo) {
		boolean result = false;
		if (userInfoDAOImpl.create(userInfo) != 0) {
			result = true;
		}
		return result;
	}

	public boolean insertImage(String path, UserInfo model) {
		Image image = new Image();
		image.setFileName(path);
		imageDAOImpl = new ImageDAOImpl();
		boolean result = false;
		image.setIdImage(imageDAOImpl.create(image));
		
		if (image.getIdImage() != 0) {
			model.setIdImageUser(image.getIdImage());
			fromSQLDateToDateUtil(model);
			userInfoDAOImpl.update(model);
			result = true;
		}
		
		return result;
	}

	public boolean updateUserInfo(UserInfo userInfo) {
		boolean result = false;
		if (userInfoDAOImpl.update(userInfo) != 0) {
			result = true;
		}
		return result;
	}
}
