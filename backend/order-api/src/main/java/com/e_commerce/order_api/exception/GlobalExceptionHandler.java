package com.e_commerce.order_api.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ApiException> handleFeignNotFoundException(FeignException.NotFound e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException apiException=new ApiException(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }
    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<ApiException> handleFeignBadRequestException(FeignException.BadRequest e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException=new ApiException(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }
    @ExceptionHandler(FeignException.MethodNotAllowed.class)
    public ResponseEntity<ApiException> handleFeignMethodNotAllowedException(FeignException.MethodNotAllowed e) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        ApiException apiException=new ApiException(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiException> handleCustomException(CustomException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException=new ApiException(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> handleGenericException(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException=new ApiException(
                ex.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }
}
