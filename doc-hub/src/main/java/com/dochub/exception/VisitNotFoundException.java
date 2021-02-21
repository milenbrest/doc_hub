package com.dochub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VisitNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public VisitNotFoundException() {
		super();
	}

	public VisitNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public VisitNotFoundException(String message) {
		super(message);
	}

	public VisitNotFoundException(Throwable cause) {
		super(cause);
	}
}
