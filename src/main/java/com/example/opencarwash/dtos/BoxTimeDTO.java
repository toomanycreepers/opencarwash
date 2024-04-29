package com.example.opencarwash.dtos;

import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
public class BoxTimeDTO {
    public UUID boxId;
    public Integer weekDay;
    public LocalTime openingTime;
    public LocalTime closingTime;
    public Boolean isOpen;
}
