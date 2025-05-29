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

import com.tka.dtos.CompanyCreateDTO;
import com.tka.dtos.CompanyResponseDTO;
import com.tka.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public CompanyResponseDTO createCompany(@Valid @RequestBody CompanyCreateDTO dto) {
        return companyService.createCompany(dto);
    }

    @GetMapping("/{id}")
    public CompanyResponseDTO getCompany(@PathVariable Long id) {
        return companyService.getCompany(id);
    }

    @PutMapping("/{id}")
    public CompanyResponseDTO updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyCreateDTO dto) {
        return companyService.updateCompany(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}