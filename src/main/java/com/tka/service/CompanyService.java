package com.tka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.CompanyCreateDTO;
import com.tka.dtos.CompanyResponseDTO;
import com.tka.entity.Company;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.CompanyRepository;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponseDTO createCompany(CompanyCreateDTO dto) {
        Company company = new Company();
        company.setName(dto.getName());
        company.setDescription(dto.getDescription());
        company.setWebsite(dto.getWebsite());
        company.setLocation(dto.getLocation());
        company.setLogoUrl(dto.getLogoUrl());
        company = companyRepository.save(company);
        return mapToCompanyResponseDTO(company);
    }

    public CompanyResponseDTO getCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return mapToCompanyResponseDTO(company);
    }

    public CompanyResponseDTO updateCompany(Long id, CompanyCreateDTO dto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        company.setName(dto.getName());
        company.setDescription(dto.getDescription());
        company.setWebsite(dto.getWebsite());
        company.setLocation(dto.getLocation());
        company.setLogoUrl(dto.getLogoUrl());
        company = companyRepository.save(company);
        return mapToCompanyResponseDTO(company);
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        companyRepository.delete(company);
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