package com.fawry.user_api.service.authentication.Impl;

import com.fawry.user_api.Exceptions.customExceptions.UserException;
import com.fawry.user_api.Exceptions.customExceptions.user.EmailAlreadyExistsException;
import com.fawry.user_api.Exceptions.customExceptions.user.PhoneAlreadyExistsException;
import com.fawry.user_api.Mapper.authentication.AuthenticationMapper;
import com.fawry.user_api.Mapper.user.UserMapper;
import com.fawry.user_api.entity.User;
import com.fawry.user_api.entity.enums.Role;
import com.fawry.user_api.model.authentication.AuthenticationRequest;
import com.fawry.user_api.model.authentication.AuthenticationResponse;
import com.fawry.user_api.model.authentication.SignUPResponse;
import com.fawry.user_api.model.authentication.UserRegistrationRequest;
import com.fawry.user_api.repository.UserRepository;
import com.fawry.user_api.security.JwtService;
import com.fawry.user_api.service.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationMapper authenticationMapper;

    @Override
    public SignUPResponse register(UserRegistrationRequest userRegistrationRequest, HttpServletResponse response) {
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email " + userRegistrationRequest.getEmail() + " is already in use.");
        }

        if (userRepository.existsByPhone(userRegistrationRequest.getPhone())) {
            throw new PhoneAlreadyExistsException("Phone number " + userRegistrationRequest.getPhone() + " is already in use.");
        }
            if (userRegistrationRequest.getRole().equals(Role.ADMIN)) {
            throw new UserException("Not allowed to register admin");
        }
        User user = authenticationMapper.toEntity(userRegistrationRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return authenticationMapper.toDTO(token);

    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest requestModel, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestModel.getEmail(), requestModel.getPassword()));

            User user = userRepository
                    .findByEmail(requestModel.getEmail())
                    .orElseThrow(() -> new UserException("Incorrect email or password"));

            String token = jwtService.generateToken(user);

            return authenticationMapper.toDTO(token,user);
        } catch (Exception e) {
            throw new UserException("Incorrect email or password");
        }
    }
}