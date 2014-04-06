package com.epam.model.bean;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Model(name = "genre_song")
public class GenreSong {
	@ColumnName(name = "id_song")
	private Integer idGenre;
	@ColumnName(name = "id_genre")
	private Integer idSong;

	public Integer getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
	}

	public Integer getIdSong() {
		return idSong;
	}

	public void setIdSong(Integer idSong) {
		this.idSong = idSong;
	}

	@Override
	public String toString() {
		return "GenreSong [idGenre=" + idGenre + ", idSong=" + idSong + "]";
	}

}
