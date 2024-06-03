package com.example.opencarwash.dtos.carwashBox;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EmployeeCwBoxDTO {
    public String id;
    public String city;
    public String street;
    public String building;
    public Integer timeSlot;
    public List<BoxDescriptionDTO> boxes;
}
