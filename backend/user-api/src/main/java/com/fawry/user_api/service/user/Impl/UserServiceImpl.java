package com.fawry.user_api.service.user.Impl;

import com.fawry.user_api.Exceptions.customExceptions.user.UserNotFoundException;
import com.fawry.user_api.Mapper.user.UserMapper;
import com.fawry.user_api.entity.User;
import com.fawry.user_api.model.user.UpdateUserRequest;
import com.fawry.user_api.model.user.UserResponse;
import com.fawry.user_api.repository.UserRepository;
import com.fawry.user_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserResponse> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        }
        UserResponse userResponse = userMapper.toDTO(user.get());
        return Optional.of(userResponse);
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userMapper.updateUserFromDTO(updateUserRequest, user);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public void deleteUser(Long id) {

    }
}

