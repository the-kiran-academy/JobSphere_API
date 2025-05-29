package com.tka.dtos;

import javax.validation.constraints.NotNull;

public class LocationCreateDTO {
    @NotNull(message = "City is required")
    private String city;

    private String state;

    @NotNull(message = "Country is required")
    private String country;

    // Default constructor
    public LocationCreateDTO() {
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}