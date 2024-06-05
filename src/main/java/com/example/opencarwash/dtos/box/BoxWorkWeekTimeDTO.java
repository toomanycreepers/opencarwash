package com.example.opencarwash.dtos.box;

import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
public class BoxWorkWeekTimeDTO {
    public String boxId;
    public LocalTime openingTime;
    public LocalTime closingTime;
}
