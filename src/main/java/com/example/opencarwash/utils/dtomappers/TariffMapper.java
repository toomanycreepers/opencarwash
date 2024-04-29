package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.TariffCreationDTO;
import com.example.opencarwash.entities.Tariff;

public class TariffMapper {
    private TariffMapper(){}

    public static Tariff mapFromDTO(TariffCreationDTO dto){
        return new Tariff(
                dto.name,
                dto.description,
                dto.commentForEmployees,
                dto.bufferTime
        );
    }
}
