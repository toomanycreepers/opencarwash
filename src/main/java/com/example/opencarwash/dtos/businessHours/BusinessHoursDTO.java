package com.example.opencarwash.dtos.businessHours;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BusinessHoursDTO {
    public String id;
    public String boxId;
    public String openingTime;
    public String closingTime;
    public Integer weekday;
    public Boolean isClosed;
}
