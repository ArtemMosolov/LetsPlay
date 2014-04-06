package com.epam.model.exception;

import java.util.List;

public class FileNotDeleteException extends Exception {

	private static final long serialVersionUID = -3938245170221678344L;

	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public FileNotDeleteException setList(List<String> list) {
		this.list = list;
		return this;
	}

	public FileNotDeleteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileNotDeleteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileNotDeleteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileNotDeleteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
