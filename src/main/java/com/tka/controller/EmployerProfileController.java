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

import com.tka.dtos.EmployerProfileCreateDTO;
import com.tka.dtos.EmployerProfileResponseDTO;
import com.tka.service.EmployerProfileService;

@RestController
@RequestMapping("/api/employer-profiles")
public class EmployerProfileController {
    private final EmployerProfileService profileService;

    @Autowired
    public EmployerProfileController(EmployerProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public EmployerProfileResponseDTO createProfile(@Valid @RequestBody EmployerProfileCreateDTO dto) {
        return profileService.createProfile(dto);
    }

    @GetMapping("/{id}")
    public EmployerProfileResponseDTO getProfile(@PathVariable Long id) {
        return profileService.getProfile(id);
    }

    @PutMapping("/{id}")
    public EmployerProfileResponseDTO updateProfile(@PathVariable Long id, @Valid @RequestBody EmployerProfileCreateDTO dto) {
        return profileService.updateProfile(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
    }
}