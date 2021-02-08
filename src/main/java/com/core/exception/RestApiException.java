package com.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class RestApiException extends RuntimeException {

    private static final long serialVersionUID = -366619390801339300L;

    public RestApiException(final String message) {
        super(message);
    }

    public RestApiException(final String msg, final Throwable throwable) {
        super(msg, throwable);
    }
}