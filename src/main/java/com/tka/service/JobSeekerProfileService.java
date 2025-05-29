package com.tka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.JobSeekerProfileCreateDTO;
import com.tka.dtos.JobSeekerProfileResponseDTO;
import com.tka.entity.JobSeekerProfile;
import com.tka.entity.User;
import com.tka.enums.Role;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.JobSeekerProfileRepository;
import com.tka.repository.SkillRepository;
import com.tka.repository.UserRepository;

@Service
public class JobSeekerProfileService {
    private final JobSeekerProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Autowired
    public JobSeekerProfileService(JobSeekerProfileRepository profileRepository, 
                                  UserRepository userRepository, 
                                  SkillRepository skillRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public JobSeekerProfileResponseDTO createProfile(JobSeekerProfileCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!user.getRole().equals(Role.JOB_SEEKER)) {
            throw new IllegalArgumentException("User must be a JOB_SEEKER");
        }
        JobSeekerProfile profile = new JobSeekerProfile();
        profile.setUser(user);
        profile.setResumeUrl(dto.getResumeUrl());
        profile.setEducation(dto.getEducation());
        profile.setExperience(dto.getExperience());
        profile.setBio(dto.getBio());
        profile = profileRepository.save(profile);
        return mapToProfileResponseDTO(profile);
    }

    public JobSeekerProfileResponseDTO getProfile(Long id) {
        JobSeekerProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        return mapToProfileResponseDTO(profile);
    }

    public JobSeekerProfileResponseDTO updateProfile(Long id, JobSeekerProfileCreateDTO dto) {
        JobSeekerProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        profile.setResumeUrl(dto.getResumeUrl());
        profile.setEducation(dto.getEducation());
        profile.setExperience(dto.getExperience());
        profile.setBio(dto.getBio());
        profile = profileRepository.save(profile);
        return mapToProfileResponseDTO(profile);
    }

    public void deleteProfile(Long id) {
        JobSeekerProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        profileRepository.delete(profile);
    }

    private JobSeekerProfileResponseDTO mapToProfileResponseDTO(JobSeekerProfile profile) {
        JobSeekerProfileResponseDTO dto = new JobSeekerProfileResponseDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setResumeUrl(profile.getResumeUrl());
        dto.setEducation(profile.getEducation());
        dto.setExperience(profile.getExperience());
        dto.setBio(profile.getBio());
        // Map skills if needed
        return dto;
    }
}