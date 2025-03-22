package com.fawry.user_api.service.user;



import com.fawry.user_api.model.user.UpdateUserRequest;
import com.fawry.user_api.model.user.UserResponse;

import java.util.Optional;

public interface UserService {


    Optional<UserResponse> getUser(Long id);
    UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest);
    void deleteUser(Long id);
}
