package com.fawry.user_api.service.authentication;

import com.fawry.user_api.model.authentication.AuthenticationRequest;
import com.fawry.user_api.model.authentication.AuthenticationResponse;
import com.fawry.user_api.model.authentication.SignUPResponse;
import com.fawry.user_api.model.authentication.UserRegistrationRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    SignUPResponse register(UserRegistrationRequest userRegistrationRequest, HttpServletResponse response);

    AuthenticationResponse login(AuthenticationRequest requestModel,HttpServletResponse response);
}
