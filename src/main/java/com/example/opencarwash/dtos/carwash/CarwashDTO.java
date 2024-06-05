package com.example.opencarwash.dtos.carwash;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CarwashDTO {

    public String id;

    public String franchiseId;

    public String city;

    public String street;

    public String building;
    public Integer timeSlot;

    public List<String> employeeIds;
}
