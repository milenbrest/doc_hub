package com.dochub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DoctorNotFoundException() {
		super();
	}

	public DoctorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DoctorNotFoundException(String message) {
		super(message);
	}

	public DoctorNotFoundException(Throwable cause) {
		super(cause);
	}
}
