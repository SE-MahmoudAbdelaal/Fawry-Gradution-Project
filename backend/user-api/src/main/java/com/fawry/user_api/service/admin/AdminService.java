package com.fawry.user_api.service.admin;



import com.fawry.user_api.entity.enums.Role;
import com.fawry.user_api.model.admin.CreateUserByAdminRequest;
import com.fawry.user_api.model.admin.UpdateUserByAdminRequest;
import com.fawry.user_api.model.admin.AdminResponse;


import java.util.List;

public interface AdminService {
    AdminResponse UpdateUserByAdmin(Long id, UpdateUserByAdminRequest updateUserByAdminRequest);
    List<AdminResponse> getUsers();
    void createUserByAdmin(CreateUserByAdminRequest createUserByAdminRequest);
    List<AdminResponse> getUsersByRole(Role role);
    void softDeleteUser(Long userId);
}
