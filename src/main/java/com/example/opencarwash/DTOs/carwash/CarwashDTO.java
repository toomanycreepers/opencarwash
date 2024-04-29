package com.example.opencarwash.DTOs.carwash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class CarwashDTO {

    public UUID id;

    public UUID franchiseId;

    public String city;

    public String street;

    public String building;

    public ArrayList<UUID> employees;
}
