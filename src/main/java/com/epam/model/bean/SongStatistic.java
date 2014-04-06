package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Model(name = "song_statistic")
public class SongStatistic implements Serializable {

	private static final long serialVersionUID = -2310195817244107825L;

	@ColumnName(name = "id_user")
	private Integer idUser;

	@ColumnName(name = "id_song")
	private Integer idSong;

	@ColumnName(name = "count")
	private Integer count;

	public Integer getIdUser() {
		return idUser;
	}

	public SongStatistic setIdUser(int idUser) {
		this.idUser = idUser;
		return this;
	}

	public Integer getIdSong() {
		return idSong;
	}

	public SongStatistic setIdSong(int idSong) {
		this.idSong = idSong;
		return this;
	}

	public Integer getCount() {
		return count;
	}

	public SongStatistic setCount(int count) {
		this.count = count;
		return this;
	}

	@Override
	public String toString() {
		return "SongStatistic [idUser=" + idUser + ", idSong=" + idSong
				+ ", count=" + count + "]";
	}

}
