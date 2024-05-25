package com.example.opencarwash.services;

import com.example.opencarwash.dtos.carwash.*;
import com.example.opencarwash.entities.Carwash;
import com.example.opencarwash.entities.Franchise;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.repositories.CarwashRepository;
import com.example.opencarwash.utils.customExceptions.AbsentFromCollectionException;
import com.example.opencarwash.utils.customExceptions.AlreadyPresentException;
import com.example.opencarwash.utils.dtomappers.CarwashMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CarwashService {
    @Autowired
    private CarwashRepository repo;
    @Autowired
    private FranchiseService franchiseService;
    @Autowired
    private UserService userService;

    public Carwash findById(UUID cwId) throws NoSuchElementException{
        return repo.findById(cwId).orElseThrow(() -> new NoSuchElementException("Invalid carwash Id."));
    }

    public void addCarwash(CarwashCreationDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException{
        UUID franchiseId = UUID.fromString(dto.franchiseId);
        Franchise franchise = franchiseService.findById(franchiseId);
        Carwash carwash = CarwashMapper.mapFromCarwashDTO(dto,franchise);
        repo.save(carwash);
    }

    public void update(CarwashUpdateDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        Carwash cw = parseAndFindById(dto.id);
        cw.setBuilding(dto.building);
        cw.setStreet(dto.street);
        cw.setCity(dto.city);
        repo.save(cw);
    }

    public void updateTimeslot(TimeslotLengthMinutesDTO dto){
        Carwash cw = parseAndFindById(dto.carwashId);
        cw.setTimeslotLengthMinutes(dto.minutes.shortValue());
        repo.save(cw);
    }

    public CarwashDTO getDTOById(UUID carwashId){
        Carwash cw = findById(carwashId);
        return CarwashMapper.mapToCarwashDTO(cw);
    }

    public List<CarwashDTO> getAllByFranchiseId(UUID id){
        List<Carwash> entities = repo.findAllByFranchiseId(id);
        List<CarwashDTO> dtos = new ArrayList<>();
        for (Carwash e : entities){
            CarwashDTO dto = CarwashMapper.mapToCarwashDTO(e);
            dtos.add(dto);
        }
        return dtos;
    }

    public void remove(UUID carwashId){
        repo.deleteById(carwashId);
    }

    public void addEmployee(EmployeeDTO dto) throws
            AlreadyPresentException,
            IllegalArgumentException,
            NoSuchElementException {
        Carwash cw = parseAndFindById(dto.carwashId);
        UUID employeeId = UUID.fromString(dto.employeeId);
        User employee = userService.findById(employeeId);
        cw.addEmployee(employee);
        repo.save(cw);
    }

    public void killEmployee(EmployeeDTO dto) throws
            AbsentFromCollectionException,
            IllegalArgumentException,
            NoSuchElementException {
        Carwash cw = parseAndFindById(dto.carwashId);
        UUID employeeId = UUID.fromString(dto.employeeId);
        User employee = userService.findById(employeeId);
        cw.removeEmployee(employee);
        repo.save(cw);
    }

    private Carwash parseAndFindById(String maybeUUID) throws
            IllegalArgumentException,
            NoSuchElementException {
        UUID id = UUID.fromString(maybeUUID);
        return findById(id);
    }
}