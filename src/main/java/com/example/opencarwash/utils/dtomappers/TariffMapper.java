package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.tariff.CwServiceOptDTO;
import com.example.opencarwash.dtos.tariff.FullTariffDTO;
import com.example.opencarwash.dtos.tariff.TariffDTO;
import com.example.opencarwash.entities.Tariff;
import com.example.opencarwash.entities.TariffOptionals;

import java.util.ArrayList;
import java.util.List;

public final class TariffMapper {
    private TariffMapper(){}

    public static TariffDTO mapToDTO(Tariff tariff){
        List<String> services = tariff.getServices().stream().map(x -> x.getId().toString()).toList();
        return new TariffDTO(
                tariff.getId().toString(),
                tariff.getName(),
                tariff.getDescription(),
                (ArrayList<String>) services,
                tariff.getCommentForEmployees(),
                tariff.getBufferTime()
        );
    }

    public static FullTariffDTO mapToFullDTO(Tariff tariff){
        List<CwServiceOptDTO> services = tariff.getServices()
                .stream()
                .map(TariffMapper::mapToOptDTO).toList();
        return new FullTariffDTO(
                tariff.getId().toString(),
                tariff.getName(),
                tariff.getDescription(),
                (ArrayList<CwServiceOptDTO>) services,
                tariff.getCommentForEmployees(),
                tariff.getBufferTime()
        );
    }

    private static CwServiceOptDTO mapToOptDTO(TariffOptionals optional){
        return new CwServiceOptDTO(
                optional.getService().getId().toString(),
                optional.getService().getName(),
                optional.getService().getPrice(),
                optional.getService().getDuration().intValue(),
                optional.getIsOptional()
        );
    }
}
