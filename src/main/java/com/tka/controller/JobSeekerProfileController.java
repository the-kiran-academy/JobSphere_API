package com.tka.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.dtos.JobSeekerProfileCreateDTO;
import com.tka.dtos.JobSeekerProfileResponseDTO;
import com.tka.service.JobSeekerProfileService;

@RestController
@RequestMapping("/api/job-seeker-profiles")
public class JobSeekerProfileController {
    private final JobSeekerProfileService profileService;

    @Autowired
    public JobSeekerProfileController(JobSeekerProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public JobSeekerProfileResponseDTO createProfile(@Valid @RequestBody JobSeekerProfileCreateDTO dto) {
        return profileService.createProfile(dto);
    }

    @GetMapping("/{id}")
    public JobSeekerProfileResponseDTO getProfile(@PathVariable Long id) {
        return profileService.getProfile(id);
    }

    @PutMapping("/{id}")
    public JobSeekerProfileResponseDTO updateProfile(@PathVariable Long id, @Valid @RequestBody JobSeekerProfileCreateDTO dto) {
        return profileService.updateProfile(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
    }
}