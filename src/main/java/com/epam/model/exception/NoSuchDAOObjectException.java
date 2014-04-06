package com.epam.model.exception;

public class NoSuchDAOObjectException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchDAOObjectException() {
		super();
	}

	public NoSuchDAOObjectException(String message) {
		super(message);
	}
}
