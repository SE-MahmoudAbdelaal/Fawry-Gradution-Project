package com.fawry.user_api.Mapper.user;

import com.fawry.user_api.entity.User;
import com.fawry.user_api.model.user.UpdateUserRequest;
import com.fawry.user_api.model.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponse toDTO(User user);
    void updateUserFromDTO(UpdateUserRequest dto, @MappingTarget User user);
}
