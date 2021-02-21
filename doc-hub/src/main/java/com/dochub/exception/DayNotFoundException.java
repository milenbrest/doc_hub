package com.dochub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DayNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DayNotFoundException() {
		super();
	}

	public DayNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DayNotFoundException(String message) {
		super(message);
	}

	public DayNotFoundException(Throwable cause) {
		super(cause);
	}
}
