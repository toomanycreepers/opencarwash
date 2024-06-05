package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.businessHours.BoxTimeDTO;
import com.example.opencarwash.dtos.box.BoxWorkWeekTimeDTO;
import com.example.opencarwash.dtos.businessHours.BusinessHoursDTO;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.entities.BusinessHours;

public final class BusinessHoursMapper {
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

    public static BusinessHoursDTO mapToBusinessHoursDTO(BusinessHours bh){
        return new BusinessHoursDTO(
                bh.getId().toString(),
                bh.getBox().getId().toString(),
                bh.getOpeningTime().toString(),
                bh.getClosingTime().toString(),
                bh.getWeekday(),
                bh.getIsClosed()
                );
    }
}
