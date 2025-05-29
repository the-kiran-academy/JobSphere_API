package com.tka.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserLoginDTO {
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Password is required")
    private String password;

    // Default constructor
    public UserLoginDTO() {
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}