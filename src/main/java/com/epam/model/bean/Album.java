package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

@Model(name = "album")
public class Album implements Serializable {

	private static final long serialVersionUID = -4697900674828199266L;

	@ColumnName(name = "id_album")
	private Integer idAlbum;

	@ColumnName(name = "name_album")
	private String nameAlbum;

	@ColumnName(name = "id_artists")
	private Integer idArtist;

	@ColumnName(name = "name_artists")
	private String nameArtists;
	
	@CompositionModel(name = "album_description")
	private AlbumDescription albumDescription;

	public String getNameArtsits() {
		return nameArtists;
	}

	public void setNameArtsits(String nameArtists) {
		this.nameArtists = nameArtists;
	}

	public AlbumDescription getAlbumDescription() {
		return albumDescription;
	}

	public void setAlbumDescription(AlbumDescription albumDescription) {
		this.albumDescription = albumDescription;
	}

	public Integer getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getNameAlbum() {
		return nameAlbum;
	}

	public void setNameAlbum(String nameAlbum) {
		this.nameAlbum = nameAlbum;
	}

	public Integer getIdArtist() {
		return idArtist;
	}

	public void setIdArtist(int idArtist) {
		this.idArtist = idArtist;
	}

	@Override
	public String toString() {
		return "Album [idAlbum=" + idAlbum + ", nameAlbum=" + nameAlbum
				+ ", idArtist=" + idArtist + ", nameArtists=" + nameArtists
				+ ", albumDescription=" + albumDescription + "]";
	}

}
