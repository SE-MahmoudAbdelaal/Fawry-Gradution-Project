package com.fawry.user_api.controller.admin;


import com.fawry.user_api.entity.enums.Role;
import com.fawry.user_api.model.admin.CreateUserByAdminRequest;
import com.fawry.user_api.model.admin.UpdateUserByAdminRequest;
import com.fawry.user_api.model.admin.AdminResponse;
import com.fawry.user_api.service.admin.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponse> updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateUserByAdminRequest updateUserByAdminRequest) {
        AdminResponse adminResponse = adminService.UpdateUserByAdmin(id, updateUserByAdminRequest);
        return new ResponseEntity<>(adminResponse, HttpStatus.OK);
    }
//    @GetMapping("/")
//    public ResponseEntity<List<AdminResponse>> getUsers() {
//        List<AdminResponse> users = adminService.getUsers();
//        return new Respo-nseEntity<>(users, HttpStatus.OK);
//    }
    @PostMapping("/")
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserByAdminRequest createUserByAdminRequest){
        adminService.createUserByAdmin(createUserByAdminRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
     @GetMapping("/")
    public ResponseEntity<List<AdminResponse>> getUsersByRole(
            @RequestParam(value = "role") Role role) {
        List<AdminResponse> users = adminService.getUsersByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> softDeleteUser(@PathVariable Long userId) {
        adminService.softDeleteUser(userId);
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }


}
