package com.e_commerce.order_api.exception;

import feign.FeignException;

public class CustomException extends RuntimeException {
    public CustomException(String message,Throwable cause) {
        super(message, cause);
    }
    public CustomException(String message) {
        super(message);
    }
}

