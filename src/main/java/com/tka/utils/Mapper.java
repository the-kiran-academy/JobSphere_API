package com.tka.utils;

import java.util.stream.Collectors;

import com.tka.dtos.CategoryResponseDTO;
import com.tka.dtos.CompanyResponseDTO;
import com.tka.dtos.JobApplicationResponseDTO;
import com.tka.dtos.JobResponseDTO;
import com.tka.dtos.LocationResponseDTO;
import com.tka.dtos.NotificationResponseDTO;
import com.tka.dtos.SkillResponseDTO;
import com.tka.dtos.UserResponseDTO;
import com.tka.entity.Category;
import com.tka.entity.Company;
import com.tka.entity.Job;
import com.tka.entity.JobApplication;
import com.tka.entity.Location;
import com.tka.entity.Notification;
import com.tka.entity.Skill;
import com.tka.entity.User;

public class Mapper {
    public static JobResponseDTO mapToJobResponseDTO(Job job) {
        if (job == null) return null;
        JobResponseDTO dto = new JobResponseDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setCompany(mapToCompanyResponseDTO(job.getCompany()));
        dto.setCategory(mapToCategoryResponseDTO(job.getCategory()));
        dto.setLocation(mapToLocationResponseDTO(job.getLocation()));
        dto.setSalaryRange(job.getSalaryRange());
        dto.setEmploymentType(job.getEmploymentType().name());
        dto.setPostedBy(mapToUserResponseDTO(job.getPostedBy()));
        dto.setPostedAt(job.getPostedAt());
        dto.setStatus(job.getStatus().name());
        dto.setSkills(job.getSkills().stream()
                .map(Mapper::mapToSkillResponseDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public static CompanyResponseDTO mapToCompanyResponseDTO(Company company) {
        if (company == null) return null;
        CompanyResponseDTO dto = new CompanyResponseDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setDescription(company.getDescription());
        dto.setWebsite(company.getWebsite());
        dto.setLocation(company.getLocation());
        dto.setLogoUrl(company.getLogoUrl());
        return dto;
    }

    public static CategoryResponseDTO mapToCategoryResponseDTO(Category category) {
        if (category == null) return null;
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public static LocationResponseDTO mapToLocationResponseDTO(Location location) {
        if (location == null) return null;
        LocationResponseDTO dto = new LocationResponseDTO();
        dto.setId(location.getId());
        dto.setCity(location.getCity());
        dto.setState(location.getState());
        dto.setCountry(location.getCountry());
        return dto;
    }

    public static UserResponseDTO mapToUserResponseDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    public static SkillResponseDTO mapToSkillResponseDTO(Skill skill) {
        if (skill == null) return null;
        SkillResponseDTO dto = new SkillResponseDTO();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        return dto;
    }

    public static NotificationResponseDTO mapToNotificationResponseDTO(Notification notification) {
        if (notification == null) return null;
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setId(notification.getId());
        dto.setUserId(notification.getUser().getId());
        dto.setMessage(notification.getMessage());
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setIsRead(notification.isRead());
        return dto;
    }

    public static JobApplicationResponseDTO mapToJobApplicationResponseDTO(JobApplication application) {
        if (application == null) return null;
        JobApplicationResponseDTO dto = new JobApplicationResponseDTO();
        dto.setId(application.getId());
        dto.setJob(application.getJob().getId() != null ? mapToJobResponseDTO(application.getJob()) : null);
        dto.setUser(application.getUser().getId() != null ? mapToUserResponseDTO(application.getUser()) : null);
        dto.setCoverLetter(application.getCoverLetter());
        dto.setStatus(application.getStatus().name());
        dto.setAppliedAt(application.getAppliedAt());
        dto.setComments(application.getComments());
        return dto;
    }
}