package com.example.opencarwash.services;

import com.example.opencarwash.dtos.order.*;
import com.example.opencarwash.dtos.tariff.FullTariffDTO;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.entities.Order;
import com.example.opencarwash.entities.Tariff;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.repositories.OrderRepository;
import com.example.opencarwash.utils.customExceptions.IllegalStatusMutationException;
import com.example.opencarwash.utils.dtomappers.OrderMapper;
import com.example.opencarwash.utils.dtomappers.TariffMapper;
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
import java.util.function.Predicate;

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

    public Order findById(String id) throws
            NoSuchElementException,
            IllegalArgumentException {
        return repo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NoSuchElementException("Incorrect order id.")
        );
    }

    public void create(OrderCreationDTO dto) throws
            IllegalArgumentException,
            DateTimeParseException,
            NoSuchElementException {
        LocalDateTime start = LocalDateTime.parse(dto.startTime);
        User client = uService.findById(dto.clientId);
        Tariff tariff = tService.findById(dto.tariffId);
        Box box = bService.findById(dto.boxId);
        Order order = new Order(start,OrderState.PLACED,tariff,box,client);
        repo.save(order);
    }

    public void remove(String id) throws IllegalArgumentException {
        UUID orderUUID = UUID.fromString(id);
        if (repo.existsById(orderUUID)) {
            repo.deleteById(orderUUID);
            return;
        }
        throw new NoSuchElementException("Nothing to delete.");
    }

    public void updateState(StateDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException,
            IndexOutOfBoundsException,
            IllegalStatusMutationException{
        Order order = findById(dto.orderId);

        checkSetState(order, dto.state);
        repo.save(order);
    }

    public void setRating(RatingDTO dto){
        Order order = findById(dto.orderId);
        order.setRating(dto.rating.shortValue());
        repo.save(order);
    }

    public void setFeedback(FeedbackDTO dto){
        Order order = findById(dto.ordedrID);
        order.setFeedback(dto.feedback);
        repo.save(order);
    }

    public void removeFeedback(String orderId) throws
            IllegalArgumentException,
            NoSuchElementException{
        Order order = findById(orderId);
        order.setFeedback(null);
        repo.save(order);
    }

    public OrderWithServicesDTO getDTOById(String id) throws
            IllegalArgumentException,
            NoSuchElementException{
        Order order = findById(id);
        return OrderMapper.mapToDTO(order);
    }

    public List<OrderWithServicesDTO> getByBoxId(String boxId) throws
            IllegalArgumentException {
        UUID id = UUID.fromString(boxId);
        List<Order> orders = repo.findAllByBoxId(id);
        return OrderMapper.mapToDTOList(orders);
    }

    public List<OrderWithServicesDTO> getBoxHistory(String boxId) throws
            IllegalArgumentException {
        UUID id = UUID.fromString(boxId);

        Predicate<Order> orderPredicate = order -> !(order.getState().equals(OrderState.PLACED)
                || order.getState().equals(OrderState.ACCEPTED));

        List<Order> orders = repo.findAllByBoxId(id).stream().filter(orderPredicate).toList();

        List<OrderWithServicesDTO> fullDTOS = new ArrayList<OrderWithServicesDTO>();
        for(Order order : orders){
            fullDTOS.add(new OrderWithServicesDTO(
                    order.getId().toString(),
                    order.getClient().getId().toString(),
                    order.getBox().getId().toString(),
                    TariffMapper.mapToFullDTO(order.getTariff()),
                    order.getStartTime().toString(),
                    order.getState().ordinal(),
                    order.getFeedback(),
                    order.getRating() == null? null : order.getRating().intValue()
            ));
        }

        return fullDTOS;
    }

    public List<OrderWithServicesDTO> getByDateBox(DateBoxDTO dto) throws
            IllegalArgumentException,
            DateTimeParseException{
        LocalDate date = LocalDate.parse(dto.date);
        UUID boxId = UUID.fromString(dto.boxId);
        List<Order> orders = repo.findByBoxAndDate(boxId, date);
        orders.removeIf(order -> !(order.getState().ordinal() == 0 || order.getState().ordinal() == 2));
        return OrderMapper.mapToDTOList(orders);
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