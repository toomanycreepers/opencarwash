package com.example.opencarwash.dtos.tariff;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class TariffDTO {
    public String id;
    public String name;
    public String description;
    public ArrayList<String> serviceIds;
    public String commentForEmployees;
    public Short BufferTime;
}
