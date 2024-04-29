package com.example.opencarwash.DTOs.User;

import java.util.ArrayList;
import java.util.UUID;

public class UserDTO {
    public UUID id;
    public String phoneNumber;
    public String firstName;
    public String lastName;
    public byte[] picture;
    public ArrayList<Short> roles;
}
