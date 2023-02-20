package com.phonebook.exceptions;

public class NotAuthorizedException extends Exception{
    public NotAuthorizedException(String msg) {
        super(msg);
    }
}
