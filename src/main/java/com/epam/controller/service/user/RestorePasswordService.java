package com.epam.controller.service.user;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.epam.controller.dao.userrestore.UserRestoreDAOImpl;
import com.epam.core.util.MD5Encrypter;
import com.epam.core.util.Validator;
import com.epam.model.bean.UserModel;
import com.epam.model.bean.UserRestore;

public class RestorePasswordService {

	private UserRestoreDAOImpl restoreDAO = new UserRestoreDAOImpl();

	private long minutesBetween(Timestamp timestamp1, Timestamp timestamp2) {
		long diff = timestamp2.getTime() - timestamp1.getTime();
		return (diff / (1000 * 60 * 60 * 24));
	}

	public boolean checkDate(String hash) {
		boolean result = false;
		Timestamp hashTimestamp = restoreDAO.getRestoreByHash(hash).getTime();
		Timestamp curTimestamp = new Timestamp(new Date().getTime());

		if (minutesBetween(curTimestamp, hashTimestamp) <= 15L) {
			result = true;
		}

		return result;
	}

	public boolean validateHash(String hash) {
		boolean result = false;

		if (Validator.MD5_CHECKSUM.validate(hash)) {
			result = true;
		}

		return result;
	}

	public String restore(String hash, String password) {
		String message = null;

		UserService userService = new UserService();
		UserRestoreDAOImpl restoreDAOImpl = new UserRestoreDAOImpl();
		UserRestore userRestore = restoreDAOImpl.getRestoreByHash(hash);

		if (userRestore != null) {
			UserModel user = userService.getUserById(userRestore.getIdUser());
			MD5Encrypter md5 = new MD5Encrypter();

			if (user != null) {
				user.setPassword(md5.encrypt(password));
				userService.updateUser(user);
				message = "PASSWORD_WAS_CHANGE";
			}
		}

		return message;
	}

	public void deleteHash(String attribute) {
		UserRestoreDAOImpl restoreDAOImpl = new UserRestoreDAOImpl();
		try {
			restoreDAOImpl.delete(restoreDAOImpl.getRestoreByHash(attribute)
					.getIdUserRestore());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
