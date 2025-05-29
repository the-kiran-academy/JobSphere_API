package com.tka.dtos;

import javax.validation.constraints.NotNull;

public class SkillCreateDTO {
    @NotNull(message = "Name is required")
    private String name;

    // Default constructor
    public SkillCreateDTO() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}