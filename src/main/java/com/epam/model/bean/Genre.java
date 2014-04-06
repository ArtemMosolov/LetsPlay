package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Model(name = "genre")
public class Genre implements Serializable {

	private static final long serialVersionUID = 2234565315565268259L;

	@ColumnName(name = "id_genre")
	private Integer idGenre;

	@ColumnName(name = "name_genre")
	private String nameGenre;

	public Integer getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}

	public String getNameGenre() {
		return nameGenre;
	}

	public void setNameGenre(String nameGenre) {
		this.nameGenre = nameGenre;
	}

	@Override
	public String toString() {
		return "Genre [idGenre=" + idGenre + ", nameGenre=" + nameGenre + "]";
	}

}
