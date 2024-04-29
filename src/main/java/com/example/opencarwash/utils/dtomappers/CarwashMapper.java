package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.CarwashCreationDTO;
import com.example.opencarwash.entities.Carwash;
import com.example.opencarwash.entities.Franchise;

public class CarwashMapper {
    private CarwashMapper(){}

    public static Carwash mapFromCarwashDTO(CarwashCreationDTO dto, Franchise franchise){
        return new Carwash(dto.name, dto.city, dto.street, dto.building, dto.timeslotLengthMinutes, franchise);
    }
}
