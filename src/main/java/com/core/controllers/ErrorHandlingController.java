package com.core.controllers;

import com.core.exception.RestApiException;
import com.core.exception.UnAuthorizedException;
import com.core.response.ServiceResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(UnAuthorizedException.class)
    public ServiceResponse catchUnAuthorizedException(final UnAuthorizedException ex) {
        log.error("UnAuthorized Exception occurred : {}", ex.getMessage());
        return new ServiceResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RestApiException.class)
    public ServiceResponse catchRestApiException(final RestApiException ex) {
        log.error("Rest Api Exception occurred : {}", ex.getMessage());
        return new ServiceResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ServiceResponse catchException(final Exception ex) {
        log.error("Exception occurred : {}", ex.getMessage());
        return new ServiceResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}