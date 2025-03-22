package com.example.product_api.exception;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
@RestControllerAdvice
public class ProductException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)//400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidMessages(MethodArgumentNotValidException ex) {
        Map<String, String> mapError = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            mapError.put(error.getObjectName(), error.getDefaultMessage());
            System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
        });
        return mapError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)//400
    @ExceptionHandler(Exception.class)
    public ApiException handleMyMethods(Exception exception, WebRequest request) {
        return new ApiException(exception.getMessage(), new Date(), request.toString());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiException handleNoSuchElementException(Exception ex, WebRequest request) {
        return new ApiException(ex.getMessage(), new Date(), request.toString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public String handleNotFoundException(String message) {
        return message
                ;
    }
}
