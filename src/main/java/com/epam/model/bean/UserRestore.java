package com.epam.model.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

@Model(name = "user_restore")
public class UserRestore implements Serializable {

	private static final long serialVersionUID = 4656276227646005346L;

	@ColumnName(name = "id_user_restore")
	private Integer idUserRestore;

	@ColumnName(name = "hash")
	private String hash;

	@ColumnName(name = "date_time")
	private Timestamp time;

	@ColumnName(name = "id_user")
	private Integer idUser;

	@CompositionModel(name = "user")
	private UserModel userModel;

	public Integer getIdUserRestore() {
		return idUserRestore;
	}

	public void setIdUserRestore(int idUserRestore) {
		this.idUserRestore = idUserRestore;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
}
