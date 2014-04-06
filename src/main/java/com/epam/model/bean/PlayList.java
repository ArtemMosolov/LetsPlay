package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Model(name = "playlist")
public class PlayList implements Serializable {

	private static final long serialVersionUID = -4203176706508760430L;

	@ColumnName(name = "id_user_play_list")
	private Integer idUserPlayList;

	@ColumnName(name = "id_song")
	private Integer idSong;

	public Integer getIdUserPlayList() {
		return idUserPlayList;
	}

	public PlayList setIdUserPlayList(int idUserPlayList) {
		this.idUserPlayList = idUserPlayList;
		return this;
	}

	public Integer getIdSong() {
		return idSong;
	}

	public PlayList setIdSong(int idSong) {
		this.idSong = idSong;
		return this;
	}

}
