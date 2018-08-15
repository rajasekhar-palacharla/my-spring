package com.userportal.exception;

public class FileStorageException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileStorageException() {
		super();
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(Throwable cause) {
		super(cause);
	}

}
