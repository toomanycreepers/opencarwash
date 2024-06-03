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
@RequestMapping("/api/carwash")
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

    @GetMapping("/{cwId}")
    public ResponseEntity<CarwashDTO> getById(@PathVariable String cwId){
        CarwashDTO carwashDTO = service.getDTOById(cwId);
        try {
            return new ResponseEntity<>(carwashDTO, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/franchiseId/{franchiseId}")
    public ResponseEntity<List<CarwashDTO>> getByFranchiseId(@PathVariable String franchiseId){
        try{
            List<CarwashDTO> carwashes = service.getAllByFranchiseId(franchiseId);
            return new ResponseEntity<>(carwashes, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> remove(@PathVariable String id){
        try{
            service.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
