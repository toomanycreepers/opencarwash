package com.example.opencarwash.dtos.tariff;

import java.util.ArrayList;

public class TariffDTO {
    public String id;
    public String name;
    public String description;
    public ArrayList<String> serviceIds;
    public String commentForEmployees;
    public Short BufferTime;
}
