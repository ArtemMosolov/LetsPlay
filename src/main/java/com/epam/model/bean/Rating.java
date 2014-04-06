package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Model(name = "rating")
public class Rating implements Serializable {

	private static final long serialVersionUID = -8436684776920334736L;

	@ColumnName(name = "id_user")
	private Integer idUser;

	@ColumnName(name = "id_song")
	private Integer idSong;

	@ColumnName(name = "like")
	private Integer like;

	@ColumnName(name = "dislike")
	private Integer dislike;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Integer getIdSong() {
		return idSong;
	}

	public void setIdSong(int idSong) {
		this.idSong = idSong;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public Integer getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	@Override
	public String toString() {
		return "Rating [idUser=" + idUser + ", idSong=" + idSong + ", like="
				+ like + ", dislike=" + dislike + "]";
	}

}
