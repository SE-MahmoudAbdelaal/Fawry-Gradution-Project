package com.fawry.user_api.Exceptions.customExceptions.user;

public class InvalidPhoneFormatException extends RuntimeException {
    public InvalidPhoneFormatException(String message) {
        super(message);
    }
}