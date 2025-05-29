package com.tka.dtos;

import javax.validation.constraints.NotNull;

public class JobApplicationCreateDTO {
    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotNull(message = "User ID is required")
    private Long userId;

    private String coverLetter;

    // Default constructor
    public JobApplicationCreateDTO() {
    }

    // Getters and Setters
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}