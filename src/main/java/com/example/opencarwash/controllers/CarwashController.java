package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.carwash.*;
import com.example.opencarwash.services.CarwashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carwash")
public class CarwashController {

    @Autowired
    private CarwashService service;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody CarwashCreationDTO dto){
        try{
            service.addCarwash(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody CarwashUpdateDTO dto){
        try{
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/timeslot")
    public ResponseEntity<HttpStatus> updateTimeslot(@RequestBody TimeslotLengthMinutesDTO dto){
        try{
            service.updateTimeslot(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addEmployee")
    public ResponseEntity<HttpStatus> addEmployee(@RequestBody EmployeeDTO dto){
        try{
            service.addEmployee(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> killEmployee(@RequestBody EmployeeDTO dto){
        try{
            service.killEmployee(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarwashDTO> getById(@PathVariable UUID id){
        CarwashDTO carwashDTO = service.getDTOById(id);
        try {
            return new ResponseEntity<>(carwashDTO, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/franchiseId/{franchiseId}")
    public ResponseEntity<List<CarwashDTO>> getByFranchiseId(@PathVariable UUID franchiseId){
        try{
            List<CarwashDTO> carwashes = service.getAllByFranchiseId(franchiseId);
            return new ResponseEntity<>(carwashes, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> remove(@PathVariable UUID id){
        try{
            service.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
