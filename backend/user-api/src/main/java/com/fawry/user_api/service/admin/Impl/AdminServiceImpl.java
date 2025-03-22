package com.fawry.user_api.service.admin.Impl;

import com.fawry.user_api.Exceptions.customExceptions.user.UserNotFoundException;
import com.fawry.user_api.Mapper.admin.AdminMapper;
import com.fawry.user_api.entity.User;
import com.fawry.user_api.entity.enums.Role;
import com.fawry.user_api.model.admin.CreateUserByAdminRequest;
import com.fawry.user_api.model.admin.UpdateUserByAdminRequest;
import com.fawry.user_api.model.admin.AdminResponse;
import com.fawry.user_api.repository.UserRepository;
import com.fawry.user_api.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AdminResponse UpdateUserByAdmin(Long id, UpdateUserByAdminRequest updateUserByAdminRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        User updatedUser=   adminMapper.updateUserByAdminFromDTO(updateUserByAdminRequest, user);
        userRepository.save(updatedUser);
        return adminMapper.toDTO(updatedUser);
    }
    @Override
    public List<AdminResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return adminMapper.toDTO(users);
    }

    @Override
    public void createUserByAdmin(CreateUserByAdminRequest createUserByAdminRequest) {
        User user =adminMapper.toEntity(createUserByAdminRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("ssssssssssssssssssssssssssssssssssss\n "+user.getPassword());
        userRepository.save(user);
    }
    @Override
    public List<AdminResponse> getUsersByRole(Role role) {
        List<User> users = userRepository.findByRoleAndDeletedAtIsNull(role);
        return adminMapper.toDTO(users);
    }

    @Override
        public void softDeleteUser(Long userId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

            user.setDeletedAt(LocalDateTime.now());
            userRepository.save(user);
        }
}
