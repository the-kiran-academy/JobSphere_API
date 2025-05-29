package com.tka.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class JobResponseDTO {
    private Long id;
    private String title;
    private String description;
    private CompanyResponseDTO company;
    private CategoryResponseDTO category;
    private LocationResponseDTO location;
    private String salaryRange;
    private String employmentType;
    private UserResponseDTO postedBy;
    private LocalDateTime postedAt;
    private String status; // OPEN, CLOSED
    private List<SkillResponseDTO> skills; // Associated skills

    // Default constructor
    public JobResponseDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CompanyResponseDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyResponseDTO company) {
        this.company = company;
    }

    public CategoryResponseDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseDTO category) {
        this.category = category;
    }

    public LocationResponseDTO getLocation() {
        return location;
    }

    public void setLocation(LocationResponseDTO location) {
        this.location = location;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public UserResponseDTO getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(UserResponseDTO postedBy) {
        this.postedBy = postedBy;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SkillResponseDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillResponseDTO> skills) {
        this.skills = skills;
    }
}