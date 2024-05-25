package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.carwashService.CwServiceCreationDTO;
import com.example.opencarwash.dtos.carwashService.CwServiceDTO;
import com.example.opencarwash.entities.Carwash;
import com.example.opencarwash.entities.CwService;

public final class CwServiceMapper {
    private CwServiceMapper(){}

    public static CwService mapFromDTO(CwServiceCreationDTO dto, Carwash carwash){
        return new CwService(dto.name, dto.price, dto.description, dto.duration, carwash);
    }

    public static CwServiceDTO mapToDTO(CwService service){
        return new CwServiceDTO(
                service.getId().toString(),
                service.getName(),
                service.getPrice(),
                service.getDescription(),
                service.getDuration().intValue(),
                service.getCw().getId().toString()
        );
    }
}
