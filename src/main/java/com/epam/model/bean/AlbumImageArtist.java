package com.epam.model.bean;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Deprecated
@Model(name = "album_image_artist")
public class AlbumImageArtist {

	@ColumnName(name = "id_album_image_artist")
	private int idAlbumImageArtist;

	@ColumnName(name = "name_album_image")
	private String nameAlbumImage;

	@ColumnName(name = "id_artists")
	private int idArtists;

	@ColumnName(name = "id_image_avatar")
	private int idImageAvatar;

	public int getIdAlbumImageArtist() {
		return idAlbumImageArtist;
	}

	public void setIdAlbumImageArtist(int idAlbumImageArtist) {
		this.idAlbumImageArtist = idAlbumImageArtist;
	}

	public String getNameAlbumImage() {
		return nameAlbumImage;
	}

	public void setNameAlbumImage(String nameAlbumImage) {
		this.nameAlbumImage = nameAlbumImage;
	}

	public int getIdArtists() {
		return idArtists;
	}

	public void setIdArtists(int idArtists) {
		this.idArtists = idArtists;
	}

	public int getIdImageAvatar() {
		return idImageAvatar;
	}

	public void setIdImageAvatar(int idImageAvatar) {
		this.idImageAvatar = idImageAvatar;
	}

	@Override
	public String toString() {
		return "AlbumImageArtist [idAlbumImageArtist=" + idAlbumImageArtist
				+ ", nameAlbumImage=" + nameAlbumImage + ", idArtists="
				+ idArtists + ", idImageAvatar=" + idImageAvatar + "]";
	}

}
