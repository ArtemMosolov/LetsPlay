package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

@Model(name = "artists")
public class Artist implements Serializable {

	private static final long serialVersionUID = 737836551090772433L;

	@ColumnName(name = "id_artists")
	private Integer idArtist;

	@ColumnName(name = "name_artists")
	private String nameArtist;

	@CompositionModel(name = "artists_description")
	private ArtistDescription artistDescription;

	public Integer getIdArtist() {
		return idArtist;
	}

	public void setIdArtist(int idArtist) {
		this.idArtist = idArtist;
	}

	public String getNameArtist() {
		return nameArtist;
	}

	public void setNameArtist(String nameArtist) {
		this.nameArtist = nameArtist;
	}

	public ArtistDescription getArtistDescription() {
		return artistDescription;
	}

	public void setArtistDescription(ArtistDescription artistDescription) {
		this.artistDescription = artistDescription;
	}

	@Override
	public String toString() {
		return "Artist [idArtist=" + idArtist + ", nameArtist=" + nameArtist
				+ ", artistDescription=" + artistDescription + "]";
	}
	
	

}
