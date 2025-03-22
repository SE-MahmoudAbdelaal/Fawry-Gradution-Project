package com.fawry.user_api.Mapper.admin;

import com.fawry.user_api.entity.User;
import com.fawry.user_api.model.admin.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdminMapper {

    User updateUserByAdminFromDTO(UpdateUserByAdminRequest dto, @MappingTarget User user);
    List<AdminResponse> toDTO(List<User> users);
    User toEntity(CreateUserByAdminRequest createUserByAdminRequest);
    AdminResponse toDTO(User user);
}

