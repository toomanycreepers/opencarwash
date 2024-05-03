package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.businessHours.BoxTimeDTO;
import com.example.opencarwash.dtos.BoxWorkWeekTimeDTO;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.entities.BusinessHours;

public class BusinessHoursMapper {
    private BusinessHoursMapper(){}

    public static BusinessHours mapFromBusinessHoursDTO(BoxTimeDTO dto, Box box)
    {
        return new BusinessHours(
                dto.openingTime, dto.closingTime,
                dto.weekDay, dto.isOpen, box);
    }

    public static BusinessHours mapFromBusinessHoursDTO(BoxWorkWeekTimeDTO dto, Box box, Integer weekDay)
    {
        return new BusinessHours(
                dto.openingTime, dto.closingTime,
                weekDay, true, box);
    }
}
