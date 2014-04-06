package com.epam.model.bean;

import java.io.Serializable;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.Model;

@Model(name = "delete_file")
public class DeleteModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@ColumnName(name = "name_file")
	private String fileName;

	@ColumnName(name = "id_delete_file")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String result) {
		this.fileName = result;
	}

}
