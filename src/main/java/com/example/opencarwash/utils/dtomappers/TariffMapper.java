package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.tariff.TariffDTO;
import com.example.opencarwash.entities.Tariff;

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
}
