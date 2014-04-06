package com.epam.controller.dao.user;

import com.epam.controller.dao.BaseDAOInterfase;

public interface UserDAO<T> extends BaseDAOInterfase<T> {
	T readLogin(String login, boolean isAdmin);

	T readEmail(String email, boolean isAdmin);
}