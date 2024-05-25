package com.example.opencarwash.dtos.order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderDTO {
    public String id;
    public String clientId;
    public String boxId;
    public String tariffId;
    public String startTime;
    public Integer state;
    public String feedback;
    public Integer rating;

}
