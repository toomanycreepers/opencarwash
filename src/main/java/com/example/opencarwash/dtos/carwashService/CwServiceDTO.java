package com.example.opencarwash.dtos.carwashService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CwServiceDTO {
    public String id;
    public String name;
    public Integer price;
    public String description;
    public Integer duration;
    public String carwashId;
}
