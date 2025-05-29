package com.tka.dtos;

import javax.validation.constraints.NotNull;

public class UserSkillCreateDTO {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Skill ID is required")
    private Long skillId;

    // Default constructor
    public UserSkillCreateDTO() {
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}