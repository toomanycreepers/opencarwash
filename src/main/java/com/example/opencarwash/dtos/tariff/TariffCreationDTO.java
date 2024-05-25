package com.example.opencarwash.dtos.tariff;

import com.example.opencarwash.utils.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;

public class TariffCreationDTO {
    public String name;
    public String description;
    public ArrayList<Tuple2<String,Boolean>> serviceIds;
}
