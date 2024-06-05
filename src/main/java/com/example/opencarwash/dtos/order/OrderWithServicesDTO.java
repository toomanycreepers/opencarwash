package com.example.opencarwash.dtos.order;

import com.example.opencarwash.dtos.carwashService.CwServiceDTO;
import com.example.opencarwash.dtos.tariff.FullTariffDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderWithServicesDTO {
    public String id;
    public String clientId;
    public String boxId;
    public FullTariffDTO fullTariff;
    public String startTime;
    public Integer state;
    public String feedback;
    public Integer rating;
}
