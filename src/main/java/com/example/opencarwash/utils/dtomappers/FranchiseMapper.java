package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.franchise.FranchiseCreationDTO;
import com.example.opencarwash.dtos.franchise.FranchiseDTO;
import com.example.opencarwash.entities.Franchise;
import com.example.opencarwash.entities.User;

public final class FranchiseMapper {
    private FranchiseMapper(){}

    public static Franchise mapFromDTO(FranchiseCreationDTO dto, User owner){
        return new Franchise(dto.name,owner);
    }

    public static FranchiseDTO mapToDTO(Franchise franchise){
        return new FranchiseDTO(
                franchise.getId().toString(),
                franchise.getName(),
                franchise.getOwner().getId().toString()
        );
    }
}
