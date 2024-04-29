package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.UserCreationDTO;
import com.example.opencarwash.entities.User;

public class UserMapper {
    private UserMapper(){}

    //TODO: Заглушка, добавить BCrypt
    public static User mapFromDTO(UserCreationDTO dto){
        return new User(dto.phoneNumber, dto.firstName, dto.lastName, dto.password);
    }
}
