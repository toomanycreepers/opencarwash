package com.example.opencarwash.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CarwashCreationDTO {
    public String name;

    public String city;

    public String street;

    public String building;

    public Short timeslotLengthMinutes;

    public String franchise_id;
}
