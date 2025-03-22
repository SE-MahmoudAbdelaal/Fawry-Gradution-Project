package com.fawry.user_api.Exceptions.customExceptions.user;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}