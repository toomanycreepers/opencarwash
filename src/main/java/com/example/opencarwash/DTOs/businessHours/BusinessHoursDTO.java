package com.example.opencarwash.DTOs.businessHours;

import java.util.UUID;

public class BusinessHoursDTO {
    public UUID id;
    public UUID boxId;
    public String openingTime;
    public String closingTime;
    public Byte weekday;
    public Boolean isClosed;
}
