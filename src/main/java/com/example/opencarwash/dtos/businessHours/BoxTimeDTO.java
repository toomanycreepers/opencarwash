package com.example.opencarwash.dtos.businessHours;

import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
public class BoxTimeDTO {
    public String boxId;
    public Integer weekDay;
    public LocalTime openingTime;
    public LocalTime closingTime;
    public Boolean isOpen;
}
