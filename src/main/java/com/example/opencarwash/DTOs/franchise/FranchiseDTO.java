package com.example.opencarwash.DTOs.franchise;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class FranchiseDTO {
    public String name;
    public UUID ownerId;
}
