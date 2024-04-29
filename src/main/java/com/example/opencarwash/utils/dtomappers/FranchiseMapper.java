package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.FranchiseCreationDTO;
import com.example.opencarwash.entities.Franchise;
import com.example.opencarwash.entities.User;

public class FranchiseMapper {
    private FranchiseMapper(){}

    public static Franchise mapFromDTO(FranchiseCreationDTO dto, User owner){
        return new Franchise(dto.name,owner);
    }
}
