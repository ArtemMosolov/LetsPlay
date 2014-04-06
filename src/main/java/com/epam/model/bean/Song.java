package com.epam.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

@Model(name = "song")
public class Song implements Serializable {

	private static final long serialVersionUID = -8392144350935474822L;

	@ColumnName(name = "id_song")
	private Integer idSong;
	@ColumnName(name = "name_song")
	private String nameSong;
	@ColumnName(name = "id_album")
	private Integer idAlbum;
	@ColumnName(name = "id_artists")
	private Integer idArtist;
	@ColumnName(name = "file_song")
	private String nameFile;
	@ColumnName(name = "name_album")
	private String nameAlbum;
	@ColumnName(name = "file_name")
	private String fileName;
	@ColumnName(name = "name_artists")
	private String nameArtists;
	@CompositionModel(name = "genre")
	private Genre genre;
	@ColumnName(name = "count")
	private Integer count;
	@ColumnName(name = "count_sum")
	private BigDecimal countBigInteger;

	public Integer getCount() {
		return count;
	}

	public Integer getCountBigInteger() {
		return countBigInteger.intValue();
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setIdSong(Integer idSong) {
		this.idSong = idSong;
	}

	public void setIdAlbum(Integer idAlbum) {
		this.idAlbum = idAlbum;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getNameAlbum() {
		return nameAlbum;
	}

	public void setNameAlbum(String nameAlbum) {
		this.nameAlbum = nameAlbum;
	}

	public String getNameArtists() {
		return nameArtists;
	}

	public void setNameArtists(String nameArtists) {
		this.nameArtists = nameArtists;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public Integer getIdSong() {
		return idSong;
	}

	public void setIdSong(int idSong) {
		this.idSong = idSong;
	}

	public String getNameSong() {
		return nameSong;
	}

	public void setNameSong(String nameSong) {
		this.nameSong = nameSong;
	}

	public Integer getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public Integer getIdArtist() {
		return idArtist;
	}

	public void setIdArtist(Integer idArtist) {
		this.idArtist = idArtist;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Song [idSong=" + idSong + ", nameSong=" + nameSong
				+ ", idAlbum=" + idAlbum + ", nameFile=" + nameFile
				+ ", nameAlbum=" + nameAlbum + ", nameArtists=" + nameArtists
				+ ", genre=" + genre + ", count=" + count + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nameFile == null) ? 0 : nameFile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (nameFile == null) {
			if (other.nameFile != null)
				return false;
		} else if (!nameFile.equals(other.nameFile))
			return false;
		return true;
	}
}
