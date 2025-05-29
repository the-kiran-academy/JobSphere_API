package com.tka.dtos;

import javax.validation.constraints.NotNull;
import java.util.List;

public class JobSeekerProfileCreateDTO {
    @NotNull(message = "User ID is required")
    private Long userId;

    private String resumeUrl;
    private String education;
    private String experience;
    private String bio;
    private List<Long> skillIds; // List of skill IDs to associate with the job seeker

    // Default constructor
    public JobSeekerProfileCreateDTO() {
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Long> skillIds) {
        this.skillIds = skillIds;
    }
}