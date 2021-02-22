package com.dochub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException()
    {
        super();
    }

    public EntityNotFoundException(Long id, Throwable cause)
    {
        super("Entity with id: " + id + " cannot be found", cause);
    }

    public EntityNotFoundException(Long id)
    {
        super("Entity with id: " + id + " cannot be found");
    }

    public EntityNotFoundException(Throwable cause)
    {
        super(cause);
    }
}
