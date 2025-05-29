package com.tka.dtos;

import java.util.List;

public class JobSeekerProfileResponseDTO {
    private Long id;
    private Long userId;
    private String resumeUrl;
    private String education;
    private String experience;
    private String bio;
    private List<SkillResponseDTO> skills; // List of associated skills

    // Default constructor
    public JobSeekerProfileResponseDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<SkillResponseDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillResponseDTO> skills) {
        this.skills = skills;
    }
}