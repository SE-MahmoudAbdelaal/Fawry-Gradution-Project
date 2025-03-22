package com.fawry.user_api.model.admin;

import com.fawry.user_api.entity.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserByAdminRequest {
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String firstName;

    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    @Pattern(regexp = "\\d{10,15}", message = "Phone number should be between 10 and 15 digits")
    private String phone;

    @Size(max = 255, message = "Photo URL must be less than or equal to 255 characters")
    private String photo = "default.jpg";

    @Email(message = "Email should be valid")
    @Size(min = 5, max = 255, message = "Email must be between 5 and 255 characters")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}

