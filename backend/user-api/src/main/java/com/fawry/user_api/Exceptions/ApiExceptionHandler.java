package com.fawry.user_api.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ApiExceptionHandler {
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;
    private Map<String, String> errors; // Field for validation errors

}
