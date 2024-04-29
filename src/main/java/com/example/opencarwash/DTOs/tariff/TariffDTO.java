package com.example.opencarwash.DTOs.tariff;

import java.util.ArrayList;
import java.util.UUID;

public class TariffDTO {
    public UUID id;
    public String name;
    public String description;
    public ArrayList<UUID> services;
    public String commentForEmployees;
    public Short BufferTime;
}
