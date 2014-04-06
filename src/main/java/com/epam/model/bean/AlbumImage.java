package com.epam.model.bean;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

@Deprecated
@Model(name = "album_image")
public class AlbumImage {

	@ColumnName(name = "id_album_image_artist")
	private int idAlbumImageArtist;

	@ColumnName(name = "id_image")
	private int idImage;

	@CompositionModel(name = "image")
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getIdAlbumImageArtist() {
		return idAlbumImageArtist;
	}

	public void setIdAlbumImageArtist(int idAlbumImageArtist) {
		this.idAlbumImageArtist = idAlbumImageArtist;
	}

	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	@Override
	public String toString() {
		return "AlbumImage [idAlbumImageArtist=" + idAlbumImageArtist
				+ ", idImage=" + idImage + "]";
	}

}
