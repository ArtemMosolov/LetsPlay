package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Model(name = "user")
public class UserModel implements Serializable {

	private static final long serialVersionUID = -6569966851258387291L;

	@ColumnName(name = "id_user")
	private Integer idUser;

	@ColumnName(name = "login")
	private String login;

	@ColumnName(name = "password")
	private String password;

	@ColumnName(name = "email")
	private String email;

	@ColumnName(name = "language")
	private String language;

	@ColumnName(name = "is_admin")
	private boolean isAdmin;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", login=" + login + ", password="
				+ password + ", email=" + email + ", language=" + language
				+ "]";
	}

}
