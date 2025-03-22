package com.fawry.user_api.Exceptions.customExceptions.user;

public class PhoneAlreadyExistsException extends RuntimeException {
    public PhoneAlreadyExistsException(String message) {
        super(message);
    }
}

