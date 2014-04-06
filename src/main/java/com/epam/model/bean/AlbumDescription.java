package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

@Model(name = "album_description")
public class AlbumDescription implements Serializable {

	private static final long serialVersionUID = -7586585116002006117L;

	@ColumnName(name = "id_album")
	private Integer idAlbumDescription;

	@ColumnName(name = "title_album")
	private String titleAlbum;

	@ColumnName(name = "description_album")
	private String descriptionAlbum;

	@ColumnName(name = "id_image")
	private Integer idImage;

	@CompositionModel(name = "image")
	private Image image;

	public String getTitleAlbum() {
		return titleAlbum;
	}

	public void setTitleAlbum(String titleAlbum) {
		this.titleAlbum = titleAlbum;
	}

	public void setIdImage(Integer idImage) {
		this.idImage = idImage;
	}

	public Integer getIdAlbumDescription() {
		return idAlbumDescription;
	}

	public void setIdAlbumDescription(int idAlbumDescription) {
		this.idAlbumDescription = idAlbumDescription;
	}

	public String getDescriptionAlbum() {
		return descriptionAlbum;
	}

	public void setDescriptionAlbum(String descriptionAlbum) {
		this.descriptionAlbum = descriptionAlbum;
	}

	public Integer getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "AlbumDescription [idAlbumDescription=" + idAlbumDescription
				+ ", titleAlbum=" + titleAlbum + ", descriptionAlbum="
				+ descriptionAlbum + ", idImage=" + idImage + ", image="
				+ image + "]";
	}
}
