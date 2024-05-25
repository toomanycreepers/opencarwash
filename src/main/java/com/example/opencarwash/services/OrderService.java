package com.example.opencarwash.services;

import com.example.opencarwash.dtos.order.*;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.entities.Order;
import com.example.opencarwash.entities.Tariff;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.repositories.OrderRepository;
import com.example.opencarwash.utils.customExceptions.IllegalStatusMutationException;
import com.example.opencarwash.utils.dtomappers.OrderMapper;
import com.example.opencarwash.utils.enums.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repo;
    @Autowired
    private TariffService tService;
    @Autowired
    private BoxService bService;
    @Autowired
    private UserService uService;

    public Order findById(UUID id){
        return repo.findById(id).orElseThrow(
                () -> new NoSuchElementException("Incorrect order id.")
        );
    }

    public void create(OrderCreationDTO dto) throws
            IllegalArgumentException,
            DateTimeParseException,
            NoSuchElementException {
        UUID tId = UUID.fromString(dto.tariffId);
        LocalDateTime start = LocalDateTime.parse(dto.startTime);
        User client = uService.findById(dto.clientId);
        Tariff tariff = tService.findById(tId);
        Box box = bService.findById(dto.boxId);
        Order order = new Order(start,OrderState.PLACED,tariff,box,client);
        repo.save(order);
    }

    public void remove(UUID id){
        repo.deleteById(id);
    }

    public void updateState(StateDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException,
            IndexOutOfBoundsException,
            IllegalStatusMutationException {
        Order order = parseAndFindById(dto.orderId);

        checkSetState(order, dto.state);

        repo.save(order);
    }

    public void setRating(RatingDTO dto){
        Order order = parseAndFindById(dto.orderId);
        order.setRating(dto.rating.shortValue());
        repo.save(order);
    }

    public void setFeedback(FeedbackDTO dto){
        Order order = parseAndFindById(dto.ordedrID);
        order.setFeedback(dto.feedback);
        repo.save(order);
    }

    public void removeFeedback(UUID orderId){
        Order order = findById(orderId);
        order.setFeedback(null);
        repo.save(order);
    }

    public OrderDTO getDTOById(UUID id) throws NoSuchElementException{
        Order order = findById(id);
        return OrderMapper.mapToDTO(order);
    }

    public List<OrderDTO> getByBoxId(UUID boxId){
        List<Order> orders = repo.findAllByBoxId(boxId);
        return OrderMapper.mapToDTOList(orders);
    }

    public List<OrderDTO> getByDateBox(DateBoxDTO dto) throws
            IllegalArgumentException,
            DateTimeParseException{
        LocalDate date = LocalDate.parse(dto.date);
        UUID boxId = UUID.fromString(dto.boxId);
        List<Order> orders = repo.findByBoxAndDate(boxId, date);
        return OrderMapper.mapToDTOList(orders);
    }

    private Order parseAndFindById(String supposedId) throws
            IllegalArgumentException,
            NoSuchElementException {
        UUID id = UUID.fromString(supposedId);
        return findById(id);
    }

    private void checkSetState(Order order, int stateOrdinal) throws
            IllegalStatusMutationException,
            IndexOutOfBoundsException{
        OrderState currentOrderState = order.getState();

        if(currentOrderState == OrderState.PLACED){
            if(stateOrdinal == OrderState.PLACED.ordinal()){
                throw new IllegalStatusMutationException();
            }
            order.setState(OrderState.values()[stateOrdinal]);
        }

        else if(currentOrderState == OrderState.ACCEPTED){
            if(stateOrdinal == OrderState.ACCEPTED.ordinal() || stateOrdinal == OrderState.PLACED.ordinal()){
                throw new IllegalStatusMutationException();
            }
            order.setState(OrderState.values()[stateOrdinal]);
        }

        else{
            throw new IllegalStatusMutationException();
        }
    }
}