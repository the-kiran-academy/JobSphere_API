package com.tka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.CompanyResponseDTO;
import com.tka.dtos.EmployerProfileCreateDTO;
import com.tka.dtos.EmployerProfileResponseDTO;
import com.tka.entity.Company;
import com.tka.entity.EmployerProfile;
import com.tka.entity.User;
import com.tka.enums.Role;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.CompanyRepository;
import com.tka.repository.EmployerProfileRepository;
import com.tka.repository.UserRepository;

@Service
public class EmployerProfileService {
    private final EmployerProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public EmployerProfileService(EmployerProfileRepository profileRepository, 
                                 UserRepository userRepository, 
                                 CompanyRepository companyRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public EmployerProfileResponseDTO createProfile(EmployerProfileCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!user.getRole().equals(Role.EMPLOYER)) {
            throw new IllegalArgumentException("User must be an EMPLOYER");
        }
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        EmployerProfile profile = new EmployerProfile();
        profile.setUser(user);
        profile.setCompany(company);
        profile.setDesignation(dto.getDesignation());
        profile = profileRepository.save(profile);
        return mapToProfileResponseDTO(profile);
    }

    public EmployerProfileResponseDTO getProfile(Long id) {
        EmployerProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        return mapToProfileResponseDTO(profile);
    }

    public EmployerProfileResponseDTO updateProfile(Long id, EmployerProfileCreateDTO dto) {
        EmployerProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        profile.setCompany(company);
        profile.setDesignation(dto.getDesignation());
        profile = profileRepository.save(profile);
        return mapToProfileResponseDTO(profile);
    }

    public void deleteProfile(Long id) {
        EmployerProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        profileRepository.delete(profile);
    }

    private EmployerProfileResponseDTO mapToProfileResponseDTO(EmployerProfile profile) {
        EmployerProfileResponseDTO dto = new EmployerProfileResponseDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setCompany(mapToCompanyResponseDTO(profile.getCompany()));
        dto.setDesignation(profile.getDesignation());
        return dto;
    }

    private CompanyResponseDTO mapToCompanyResponseDTO(Company company) {
        CompanyResponseDTO dto = new CompanyResponseDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setDescription(company.getDescription());
        dto.setWebsite(company.getWebsite());
        dto.setLocation(company.getLocation());
        dto.setLogoUrl(company.getLogoUrl());
        return dto;
    }
}