package com.tka.dtos;

import java.time.LocalDateTime;

public class JobApplicationResponseDTO {
    private Long id;
    private JobResponseDTO job;
    private UserResponseDTO user;
    private LocalDateTime appliedAt;
    private String status; // APPLIED, REVIEWED, ACCEPTED, REJECTED
    private String coverLetter;
    private String comments;

    // Default constructor
    public JobApplicationResponseDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobResponseDTO getJob() {
        return job;
    }

    public void setJob(JobResponseDTO job) {
        this.job = job;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
    
}