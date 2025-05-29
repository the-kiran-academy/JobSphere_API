package com.tka.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.LocationCreateDTO;
import com.tka.dtos.LocationResponseDTO;
import com.tka.entity.Location;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.LocationRepository;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationResponseDTO createLocation(LocationCreateDTO dto) {
        Location location = new Location();
        location.setCity(dto.getCity());
        location.setState(dto.getState());
        location.setCountry(dto.getCountry());
        location = locationRepository.save(location);
        return mapToLocationResponseDTO(location);
    }

    public LocationResponseDTO getLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found"));
        return mapToLocationResponseDTO(location);
    }

    public List<LocationResponseDTO> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(this::mapToLocationResponseDTO)
                .collect(Collectors.toList());
    }

    private LocationResponseDTO mapToLocationResponseDTO(Location location) {
        LocationResponseDTO dto = new LocationResponseDTO();
        dto.setId(location.getId());
        dto.setCity(location.getCity());
        dto.setState(location.getState());
        dto.setCountry(location.getCountry());
        return dto;
    }
}