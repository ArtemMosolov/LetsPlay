package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

@Model(name = "artists_description")
public class ArtistDescription implements Serializable {

	private static final long serialVersionUID = 7753110000577695739L;

	@ColumnName(name = "id_artists")
	private Integer idArtistDescription;

	@ColumnName(name = "title")
	private String title;

	@ColumnName(name = "artists_description")
	private String artistDescription;

	@ColumnName(name = "id_image")
	private Integer idImage;

	@CompositionModel(name = "image")
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Integer getIdArtistDescription() {
		return idArtistDescription;
	}

	public void setIdArtistDescription(int idArtistDescription) {
		this.idArtistDescription = idArtistDescription;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtistDescription() {
		return artistDescription;
	}

	public void setArtistDescription(String artistDescription) {
		this.artistDescription = artistDescription;
	}

	public Integer getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public void setIdArtistDescription(Integer idArtistDescription) {
		this.idArtistDescription = idArtistDescription;
	}

	public void setIdImage(Integer idImage) {
		this.idImage = idImage;
	}

	@Override
	public String toString() {
		return "ArtistDescription [idArtistDescription=" + idArtistDescription
				+ ", title=" + title + ", artistDescription="
				+ artistDescription + ", idImage=" + idImage + ", image="
				+ image + "]";
	}

}
