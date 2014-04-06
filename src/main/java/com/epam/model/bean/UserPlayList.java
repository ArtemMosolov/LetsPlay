package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Model(name = "user_play_list")
public class UserPlayList implements Serializable {

	private static final long serialVersionUID = -1298474874659412922L;

	@ColumnName(name = "id_user_play_list")
	private Integer idUserPlayList;

	@ColumnName(name = "name_play_list")
	private String namePlayList;

	@ColumnName(name = "id_user")
	private Integer idUser;

	public Integer getIdUserPlayList() {
		return idUserPlayList;
	}

	public UserPlayList setIdUserPlayList(int idUserPlayList) {
		this.idUserPlayList = idUserPlayList;
		return this;
	}

	public String getNamePlayList() {
		return namePlayList;
	}

	public UserPlayList setNamePlayList(String namePlayList) {
		this.namePlayList = namePlayList;
		return this;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public UserPlayList setIdUser(int idUser) {
		this.idUser = idUser;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result
				+ ((idUserPlayList == null) ? 0 : idUserPlayList.hashCode());
		result = prime * result
				+ ((namePlayList == null) ? 0 : namePlayList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPlayList other = (UserPlayList) obj;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (idUserPlayList == null) {
			if (other.idUserPlayList != null)
				return false;
		} else if (!idUserPlayList.equals(other.idUserPlayList))
			return false;
		if (namePlayList == null) {
			if (other.namePlayList != null)
				return false;
		} else if (!namePlayList.equals(other.namePlayList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPlayList [idUserPlayList=" + idUserPlayList
				+ ", namePlayList=" + namePlayList + ", idUser=" + idUser + "]";
	}

}
