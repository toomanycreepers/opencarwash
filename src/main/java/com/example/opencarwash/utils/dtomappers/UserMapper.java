package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.UserCreationDTO;
import com.example.opencarwash.entities.User;


public class UserMapper {
    private UserMapper(){}

    public static User mapFromDTO(UserCreationDTO dto){
        User user = new User(dto.phoneNumber, dto.firstName, dto.lastName, dto.password);
        user.setPassword(dto.password);
        return user;
    }
}
