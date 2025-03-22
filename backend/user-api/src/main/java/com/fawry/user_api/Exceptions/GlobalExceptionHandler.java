package com.fawry.user_api.Exceptions;

import com.fawry.user_api.Exceptions.customExceptions.UserException;
import com.fawry.user_api.Exceptions.customExceptions.user.EmailAlreadyExistsException;
import com.fawry.user_api.Exceptions.customExceptions.user.PhoneAlreadyExistsException;
import com.fawry.user_api.Exceptions.customExceptions.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiExceptionHandler> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String message = String.format("The value '%s' for parameter '%s' is not valid.", ex.getValue(), ex.getName());
        logError(ex, message);
        return buildResponseEntity("Invalid parameter value.", HttpStatus.BAD_REQUEST, createErrorMap(ex.getName(), message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionHandler> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logError(ex, "Validation failed for one or more fields.");
        return buildResponseEntity("Validation failed for one or more fields.", HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiExceptionHandler> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errors = extractJsonParseErrors(ex);
        logError(ex, "JSON parsing error with possible enumeration issues.");
        return buildResponseEntity("JSON parsing error.", HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiExceptionHandler> handleUserNotFoundException(UserNotFoundException ex) {
        logError(ex, "User not found.");
        return buildResponseEntity("User not found.", HttpStatus.NOT_FOUND, createErrorMap("user", ex.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiExceptionHandler> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        logError(ex, "Email already in use.");
        return buildResponseEntity("Email already in use.", HttpStatus.CONFLICT, createErrorMap("email", ex.getMessage()));
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<ApiExceptionHandler> handlePhoneAlreadyExistsException(PhoneAlreadyExistsException ex) {
        logError(ex, "Phone number already in use.");
        return buildResponseEntity("Phone number already in use.", HttpStatus.CONFLICT, createErrorMap("phone", ex.getMessage()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiExceptionHandler> handleUserException(UserException ex) {
        logError(ex, "User error.");
        return buildResponseEntity("Incorrect email or password.", HttpStatus.UNAUTHORIZED, createErrorMap("authentication", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionHandler> handleGenericException(Exception ex) {
        String message = "An unexpected error occurred.";
        logError(ex, message);
        return buildResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR, createErrorMap("exception", "Internal Server Error. Please contact support if the issue persists."));
    }

    private ResponseEntity<ApiExceptionHandler> buildResponseEntity(String message, HttpStatus status, Map<String, String> errors) {
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler(
                message,
                status,
                LocalDateTime.now(),
                errors
        );
        return new ResponseEntity<>(apiExceptionHandler, status);
    }

    private Map<String, String> createErrorMap(String field, String message) {
        Map<String, String> errors = new HashMap<>();
        errors.put(field, message);
        return errors;
    }

    private void logError(Exception ex, String message) {
        logger.error("{}: {}", message, ex.getMessage(), ex);
    }

    private Map<String, String> extractJsonParseErrors(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        String rootCauseMessage = ex.getMostSpecificCause().getMessage();

        // Define a mapping of enum types to acceptable values
        Map<String, String> enumMappings = new HashMap<>();
        enumMappings.put("Role", "MERCHANT, USER");
        enumMappings.put("Gender", "MALE, FEMALE, OTHER");

        // Check for enum deserialization errors
        for (Map.Entry<String, String> entry : enumMappings.entrySet()) {
            String enumType = entry.getKey();
            String acceptedValues = entry.getValue();

            if (rootCauseMessage.contains("Cannot deserialize value of type `com.fawry.user_api.entity.enums." + enumType + "`")) {
                if (rootCauseMessage.contains("from String \"\"")) {
                    errors.put(enumType.toLowerCase(), enumType + " cannot be empty. Accepted values are: " + acceptedValues + ".");
                } else {
                    errors.put(enumType.toLowerCase(), "Invalid " + enumType.toLowerCase() + " value. Accepted values are: " + acceptedValues + ".");
                }
            }
        }

        if (errors.isEmpty()) {
            errors.put("jsonParseError", "Invalid JSON data format. Ensure all fields are correct.");
        }

        return errors;
    }
}
