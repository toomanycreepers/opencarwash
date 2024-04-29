package com.example.opencarwash.DTOs.box;

import java.util.ArrayList;
import java.util.UUID;

public class BoxDTO {
    public UUID id;
    public UUID carwashId;
    public Short number;
    public ArrayList<UUID> tariffs;
}
