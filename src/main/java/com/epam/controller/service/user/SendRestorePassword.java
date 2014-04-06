package com.epam.controller.service.user;

import java.sql.Timestamp;
import java.util.Date;

import com.epam.controller.dao.userrestore.UserRestoreDAOImpl;
import com.epam.core.util.MD5Encrypter;
import com.epam.core.util.MailSender;
import com.epam.model.bean.UserModel;
import com.epam.model.bean.UserRestore;

public class SendRestorePassword {

	UserRestoreDAOImpl restoreDAO = new UserRestoreDAOImpl();

	public String sendMail(String email) {
		String message = null;
		UserModel user = new UserService().getUserByEmail(email);

		if (user != null) {
			MailSender sender = new MailSender();
			Timestamp curTime = new Timestamp(new Date().getTime());
			String hash = new MD5Encrypter().encrypt(curTime + user.getEmail());
			String mail = "http://localhost:8080/LetsPlay/restorepassword?hash="
					+ hash;

			UserRestore restore = new UserRestore();
			restore.setHash(hash);
			restore.setTime(curTime);
			restore.setIdUser(user.getIdUser());

			restoreDAO.create(restore);

			sender.send(user.getEmail(), "LetsPlay. Restore yours password",
					mail);
		} else {
			message = "SOME_ERROR_IN_SEND";
		}

		return message;
	}
}
