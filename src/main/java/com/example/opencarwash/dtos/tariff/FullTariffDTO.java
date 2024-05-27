package com.example.opencarwash.dtos.tariff;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class FullTariffDTO {
    public String id;
    public String name;
    public String description;
    public ArrayList<CwServiceOptDTO> services;
    public String commentForEmployees;
    public Short BufferTime;
}
