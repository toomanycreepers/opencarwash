package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.dtos.order.OrderDTO;
import com.example.opencarwash.entities.Order;

import java.util.ArrayList;
import java.util.List;

public final class OrderMapper {
    private OrderMapper(){}

    public static OrderDTO mapToDTO(Order order){
        return new OrderDTO(
                order.getId().toString(),
                order.getClient().getId().toString(),
                order.getBox().getId().toString(),
                order.getTariff().getId().toString(),
                order.getStartTime().toString(),
                order.getState().ordinal(),
                order.getFeedback(),
                order.getRating().intValue());
    }

    public static List<OrderDTO> mapToDTOList(List<Order> orders){
        List<OrderDTO> dtos = new ArrayList<>();
        for (Order o : orders){
            dtos.add(OrderMapper.mapToDTO(o));
        }
        return dtos;
    }
}
