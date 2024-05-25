package com.example.opencarwash.dtos.carwashService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CwServiceCreationDTO {
    public String name;
    public Integer price;
    public String description;
    public Short duration;
    public String carwashId;
}
