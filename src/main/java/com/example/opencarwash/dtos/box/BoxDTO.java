package com.example.opencarwash.dtos.box;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BoxDTO {
    public String id;
    public String carwashId;
    public Integer number;
    public List<String> tariffIds;
}
