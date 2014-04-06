package com.epam.model.exception;

public class NoSuchDAOTypeException extends Exception {
	private static final long serialVersionUID = -695626343L;

	public NoSuchDAOTypeException() {
		super();
	}

	public NoSuchDAOTypeException(String message) {
		super(message);
	}
}
