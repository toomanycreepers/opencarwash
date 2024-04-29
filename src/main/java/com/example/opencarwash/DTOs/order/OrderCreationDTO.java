package com.example.opencarwash.DTOs.order;

import java.util.UUID;

public class OrderCreationDTO {
    public UUID clientId;
    public UUID boxId;
    public UUID tariffId;
    public String startTime;
}
