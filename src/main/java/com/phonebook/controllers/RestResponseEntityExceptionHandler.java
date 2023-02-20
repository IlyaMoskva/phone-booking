package com.phonebook.controllers;

import com.phonebook.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ApiNotFoundException.class })
    public ResponseEntity<Object> handleApiNotFoundException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                "Can't get response from remote API. " + ex.getMessage(), new HttpHeaders(), ((ApiNotFoundException)ex).getStatus());
    }
    @ExceptionHandler({ DeviceAlreadyInUseException.class })
    public ResponseEntity<Object> handleAlreadyInUseException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                "Device is already in use " + ex.getMessage() + " and can't be booked until returned."
                        , new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ DeviceNotFoundException.class })
    public ResponseEntity<Object> handleDeviceNotFoundException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                "Device with id: " + ex.getMessage() + " not found in repository.", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ NotAuthorizedException.class })
    public ResponseEntity<Object> handleNotAuthorizedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                "User is not authorized or header is not provided.", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ InternalException.class })
    public ResponseEntity<Object> handleInternalException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}