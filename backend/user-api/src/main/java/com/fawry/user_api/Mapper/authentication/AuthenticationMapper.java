package com.fawry.user_api.Mapper.authentication;

import com.fawry.user_api.entity.User;
import com.fawry.user_api.model.authentication.AuthenticationResponse;
import com.fawry.user_api.model.authentication.SignUPResponse;
import com.fawry.user_api.model.authentication.UserRegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthenticationMapper {
    User toEntity(UserRegistrationRequest dto);
    AuthenticationResponse toDTO(String token, User user);
    SignUPResponse toDTO(String token);
}
