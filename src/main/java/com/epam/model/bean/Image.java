package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Model(name = "image")
public class Image implements Serializable {

	private static final long serialVersionUID = 5292866050720125560L;

	@ColumnName(name = "id_image")
	private Integer idImage;

	@ColumnName(name = "file_name")
	private String fileName;

	public Image() {
	}

	public Image(String string) {
		fileName = string;
	}

	public Integer getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Image [idImage=" + idImage + ", fileName=" + fileName + "]";
	}

}
