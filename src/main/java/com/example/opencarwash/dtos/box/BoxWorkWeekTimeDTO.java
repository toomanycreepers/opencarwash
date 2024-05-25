package com.example.opencarwash.dtos.box;

import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
public class BoxWorkWeekTimeDTO {
    public UUID boxId;
    public LocalTime openingTime;
    public LocalTime closingTime;
}
