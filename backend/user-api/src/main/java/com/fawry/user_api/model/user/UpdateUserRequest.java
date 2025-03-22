package com.fawry.user_api.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @Size(max = 50, message = "First name must be less than or equal to 50 characters")
    private String firstName;

    @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
    private String lastName;

    @Pattern(regexp = "\\d{10,15}", message = "Phone number should be between 10 and 15 digits")
    private String phone;

    @Size(max = 255, message = "Photo URL must be less than or equal to 255 characters")
    private String photo = "default.jpg";

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be less than or equal to 100 characters")
    private String email;
}
