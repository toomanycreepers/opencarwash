package com.example.opencarwash.DTOs.carwash;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@NotBlank
public class CarwashCreationDTO {

    public UUID franchiseId;

    public String city;

    public String street;

    public String building;
}
