package com.tka.dtos;

import javax.validation.constraints.NotNull;

public class JobSkillCreateDTO {
    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotNull(message = "Skill ID is required")
    private Long skillId;

    // Default constructor
    public JobSkillCreateDTO() {
    }

    // Getters and Setters
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}