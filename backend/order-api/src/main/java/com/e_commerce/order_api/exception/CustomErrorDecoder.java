package com.e_commerce.order_api.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String errorMessage = "Unknown error";
        if (response.body() != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }
                errorMessage = responseBody.toString();
            } catch (IOException e) {
                errorMessage = "Error reading response body";
            }
        }
        switch (response.status()) {
            case 404:
                return new CustomException("Resource not found: " + methodKey + " - " + errorMessage);
            case 500:
                return new CustomException("Server error: " + methodKey + " - " + errorMessage);
            case 400:
                return new CustomException("Bad Request not found: " + methodKey + " - " + errorMessage);
            default:
                return new CustomException("Custom error message: " + methodKey + " - " + errorMessage);
        }
    }
}
