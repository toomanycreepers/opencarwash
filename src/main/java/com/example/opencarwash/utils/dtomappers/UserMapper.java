package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.UserCreationDTO;
import com.example.opencarwash.dtos.user.UserDTO;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.entities.Role;
import com.example.opencarwash.utils.dtomappers.RoleMapper;

import java.util.ArrayList;
import java.util.stream.Collectors;



public class UserMapper {
    private UserMapper(){}

    public static User mapFromDTO(UserCreationDTO dto){
        User user = new User(dto.phoneNumber, dto.firstName, dto.lastName, dto.password);
        user.setPassword(dto.password);
        return user;
    }

    public static UserDTO mapToDTO(User user) throws IndexOutOfBoundsException{
        return new UserDTO(user.getId().toString(),
                user.getPhoneNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getPicture(),
                new ArrayList<>(user.getUserRoles().stream()
                        .map(Role::getAuthority)
                        .map(RoleMapper::roleNameToNumber)
                        .collect(Collectors.toList())));
    }
}
