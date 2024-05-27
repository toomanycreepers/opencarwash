package com.example.opencarwash.dtos.tariff;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CwServiceOptDTO {
    public String id;
    public String name;
    public Integer price;
    public Integer duration;
    public Boolean isOptional;
}
