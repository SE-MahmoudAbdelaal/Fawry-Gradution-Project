package com.fawry.user_api.Exceptions.customExceptions.user;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}