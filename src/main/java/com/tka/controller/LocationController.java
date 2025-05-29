package com.tka.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.dtos.LocationCreateDTO;
import com.tka.dtos.LocationResponseDTO;
import com.tka.service.LocationService;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public LocationResponseDTO createLocation(@Valid @RequestBody LocationCreateDTO dto) {
        return locationService.createLocation(dto);
    }

    @GetMapping("/{id}")
    public LocationResponseDTO getLocation(@PathVariable Long id) {
        return locationService.getLocation(id);
    }

    @GetMapping
    public List<LocationResponseDTO> getAllLocations() {
        return locationService.getAllLocations();
    }
}