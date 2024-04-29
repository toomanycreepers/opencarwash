package com.example.opencarwash.DTOs.order;

import java.util.UUID;

public class OrderDTO {
    public UUID id;
    public UUID clientId;
    public UUID boxId;
    public UUID tariffId;
    public String startTime;
    public Short state;
    public String feedback;
    public Short rating;

}
