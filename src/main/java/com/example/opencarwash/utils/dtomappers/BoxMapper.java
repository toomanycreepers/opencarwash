package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.box.BoxDTO;
import com.example.opencarwash.dtos.carwashBox.BoxDescriptionDTO;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.entities.Tariff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class BoxMapper {
    private BoxMapper(){}

    public static BoxDTO toDTO(Box box){
        return new BoxDTO(
                box.getId().toString(),
                box.getCarwash().getId().toString(),
                box.getNumber(),
                getBoxTariffIds(box.getTariffs())
        );
    }

    private static List<String> getBoxTariffIds(Collection<Tariff> tariffs){
        List<String> ids = new ArrayList<>();
        for (Tariff tariff: tariffs){
            ids.add(tariff.getId().toString());
        }
        return ids;
    }

    public static BoxDescriptionDTO mapDTOtoDescriptionDTO(BoxDTO dto, boolean status){
        return new BoxDescriptionDTO(
                dto.id, dto.number, status
        );
    }
}
