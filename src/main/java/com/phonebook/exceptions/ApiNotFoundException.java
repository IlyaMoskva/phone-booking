package com.phonebook.exceptions;

import org.springframework.http.HttpStatus;

public class ApiNotFoundException extends Exception{

    private HttpStatus status;
    public ApiNotFoundException(String msg) {
        super(msg);
    }

    public ApiNotFoundException(String msg, int statusCode) {
        super(msg);
        this.status = HttpStatus.resolve(statusCode);
    }
    public HttpStatus getStatus() {
        return status;
    }
}
