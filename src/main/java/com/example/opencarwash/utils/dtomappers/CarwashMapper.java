package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.carwash.CarwashCreationDTO;
import com.example.opencarwash.dtos.carwash.CarwashDTO;
import com.example.opencarwash.entities.Carwash;
import com.example.opencarwash.entities.Franchise;
import com.example.opencarwash.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class CarwashMapper {
    private CarwashMapper(){}

    public static Carwash mapFromCarwashDTO(CarwashCreationDTO dto, Franchise franchise){
        return new Carwash(dto.city, dto.street, dto.building, franchise);
    }

    public static CarwashDTO mapToCarwashDTO(Carwash cw){
        List<String> employeeIds = new ArrayList<>();
        for (User employee : cw.getEmployees()){
            employeeIds.add(employee.getId().toString());
        }

        return new CarwashDTO(
                cw.getId().toString(),
                cw.getFranchise().getId().toString(),
                cw.getCity(),
                cw.getStreet(),
                cw.getBuilding(),
                employeeIds);
    }
}
