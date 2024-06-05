package com.example.opencarwash.dtos.tariff;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TariffDTO {
    public String id;
    public String name;
    public String description;
    public List<String> serviceIds;
    public String commentForEmployees;
    public Short BufferTime;
}
