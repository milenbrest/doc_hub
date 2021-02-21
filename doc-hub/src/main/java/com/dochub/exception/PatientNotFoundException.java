package com.dochub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PatientNotFoundException() {
		super();
	}

	public PatientNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientNotFoundException(String message) {
		super(message);
	}

	public PatientNotFoundException(Throwable cause) {
		super(cause);
	}
}
