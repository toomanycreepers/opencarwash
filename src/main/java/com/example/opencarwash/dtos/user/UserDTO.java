package com.example.opencarwash.dtos.user;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class UserDTO {
    public String id;
    public String phoneNumber;
    public String firstName;
    public String lastName;
    public byte[] picture;
    public ArrayList<String> roles;
}
