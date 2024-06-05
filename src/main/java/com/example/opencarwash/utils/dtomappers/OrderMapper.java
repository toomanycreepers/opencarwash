package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.order.OrderWithServicesDTO;
import com.example.opencarwash.entities.Order;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public final class OrderMapper {
    private OrderMapper(){}

    public static OrderWithServicesDTO mapToDTO(Order order){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return new OrderWithServicesDTO(
                order.getId().toString(),
                order.getClient().getId().toString(),
                order.getBox().getId().toString(),
                TariffMapper.mapToFullDTO(order.getTariff()),
                order.getStartTime().format(dateTimeFormatter),
                order.getState().ordinal(),
                order.getFeedback(),
                order.getRating() == null? null : order.getRating().intValue());
    }

    public static List<OrderWithServicesDTO> mapToDTOList(List<Order> orders){
        List<OrderWithServicesDTO> dtos = new ArrayList<>();
        for (Order o : orders){
            dtos.add(OrderMapper.mapToDTO(o));
        }
        return dtos;
    }
}
