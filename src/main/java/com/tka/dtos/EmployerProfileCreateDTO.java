package com.tka.dtos;

import javax.validation.constraints.NotNull;

public class EmployerProfileCreateDTO {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Company ID is required")
    private Long companyId;

    private String designation;

    // Default constructor
    public EmployerProfileCreateDTO() {
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}