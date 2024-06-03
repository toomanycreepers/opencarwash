package com.example.opencarwash.dtos.tariff;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FullTariffDTO {
    public String id;
    public String name;
    public String description;
    public List<CwServiceOptDTO> services;
    public String commentForEmployees;
    public Short BufferTime;
}
