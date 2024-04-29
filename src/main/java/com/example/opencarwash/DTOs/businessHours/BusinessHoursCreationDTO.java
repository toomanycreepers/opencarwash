package com.example.opencarwash.DTOs.businessHours;

import java.util.UUID;

public class BusinessHoursCreationDTO {
    public UUID boxId;
    public String openingTime;
    public String closingTime;
    public Byte weekday;
}
