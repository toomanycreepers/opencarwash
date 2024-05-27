package com.example.opencarwash.services;

import com.example.opencarwash.dtos.carwash.*;
import com.example.opencarwash.entities.Carwash;
import com.example.opencarwash.entities.Franchise;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.repositories.CarwashRepository;
import com.example.opencarwash.utils.customExceptions.AbsentFromCollectionException;
import com.example.opencarwash.utils.customExceptions.AlreadyPresentException;
import com.example.opencarwash.utils.dtomappers.CarwashMapper;
import com.example.opencarwash.utils.dtomappers.UserMapper;
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

    public Carwash findById(String cwId) throws
            NoSuchElementException,
            IllegalArgumentException{
        UUID cwUUID = UUID.fromString(cwId);
        return repo.findById(cwUUID).orElseThrow(
                () -> new NoSuchElementException("Invalid carwash Id.")
        );
    }

    public void addCarwash(CarwashCreationDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException{
        Franchise franchise = franchiseService.findById(dto.franchiseId);
        Carwash carwash = CarwashMapper.mapFromCarwashDTO(dto,franchise);
        repo.save(carwash);
    }

    public void update(CarwashUpdateDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        Carwash cw = findById(dto.id);
        cw.setBuilding(dto.building);
        cw.setStreet(dto.street);
        cw.setCity(dto.city);
        repo.save(cw);
    }

    public void updateTimeslot(TimeslotLengthMinutesDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException{
        Carwash cw = findById(dto.carwashId);
        cw.setTimeslotLengthMinutes(dto.minutes.shortValue());
        repo.save(cw);
    }

    public CarwashDTO getDTOById(String carwashId) throws
            NoSuchElementException,
            IllegalArgumentException {
        Carwash cw = findById(carwashId);
        return CarwashMapper.mapToCarwashDTO(cw);
    }

    public List<CarwashDTO> getAllByFranchiseId(String fId) throws
            IllegalArgumentException{
        UUID franchiseUUID = UUID.fromString(fId);
        List<Carwash> entities = repo.findAllByFranchiseId(franchiseUUID);
        List<CarwashDTO> dtos = new ArrayList<>();
        for (Carwash e : entities){
            CarwashDTO dto = CarwashMapper.mapToCarwashDTO(e);
            dtos.add(dto);
        }
        return dtos;
    }

    public void remove(String carwashId) throws
            NoSuchElementException,
            IllegalArgumentException{
        UUID cwUUID = UUID.fromString(carwashId);
        if (repo.existsById(cwUUID)) {
            repo.deleteById(cwUUID);
            return;
        }
        throw new NoSuchElementException("Nothing to delete.");
    }

    public void addEmployee(EmployeeDTO dto) throws
            AlreadyPresentException,
            IllegalArgumentException,
            NoSuchElementException {
        Carwash cw = findById(dto.carwashId);

        User employee = userService.findById(dto.employeeId);
        cw.addEmployee(employee);
        repo.save(cw);
    }

    public void killEmployee(EmployeeDTO dto) throws
            AbsentFromCollectionException,
            IllegalArgumentException,
            NoSuchElementException {
        Carwash cw = findById(dto.carwashId);
        UUID employeeId = UUID.fromString(dto.employeeId);
        User employee = userService.findById(employeeId.toString());
        cw.removeEmployee(employee);
        repo.save(cw);
    }
}