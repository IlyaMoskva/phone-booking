package com.phonebook.exceptions;

public class DeviceAlreadyInUseException extends Exception {
    public DeviceAlreadyInUseException(String msg) {
        super(msg);
    }
}
