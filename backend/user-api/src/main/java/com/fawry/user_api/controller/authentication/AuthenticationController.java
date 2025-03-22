package com.fawry.user_api.controller.authentication;

import com.fawry.user_api.model.authentication.AuthenticationRequest;
import com.fawry.user_api.model.authentication.AuthenticationResponse;
import com.fawry.user_api.model.authentication.SignUPResponse;
import com.fawry.user_api.model.authentication.UserRegistrationRequest;
import com.fawry.user_api.service.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<SignUPResponse> register(
            @Valid @RequestBody UserRegistrationRequest userRegistrationRequest, HttpServletResponse response) {
        SignUPResponse authResponse = authenticationService.register(userRegistrationRequest, response);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        AuthenticationResponse authResponse = authenticationService.login(authenticationRequest, response);
        return ResponseEntity.ok(authResponse);
    }
}
