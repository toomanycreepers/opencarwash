package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.CwServiceCreationDTO;
import com.example.opencarwash.entities.CwService;

public class CwServiceMapper {
    private CwServiceMapper(){}

    public static CwService mapFromDTO(CwServiceCreationDTO dto){
        return new CwService(dto.name, dto.price, dto.description, dto.duration);
    }
}
