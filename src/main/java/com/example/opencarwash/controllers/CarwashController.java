package com.example.opencarwash.controllers;

import com.example.opencarwash.DTOs.carwash.CarwashCreationDTO;
import com.example.opencarwash.DTOs.carwash.CarwashDTO;
import com.example.opencarwash.DTOs.carwash.EmployeeDTO;
import com.example.opencarwash.DTOs.carwash.TimeslotLengthMinutesDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/carwash")
public class CarwashController {

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody CarwashCreationDTO dto){
        try{
            service.create(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody CarwashCreationDTO dto){
        try{
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateTimeslot(@RequestBody TimeslotLengthMinutesDTO dto){
        try{
            service.updateTimeslot(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<HttpStatus> addEmployee(@RequestBody EmployeeDTO dto){
        try{
            service.addEmployee(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
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
        CarwashDTO carwashDTO = service.getById(id);
        try {
            return new ResponseEntity<>(carwash, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/franchiseId/{franchiseId}")
    public ResponseEntity<ArrayList<CarwashDTO>> getByFranchiseId(@PathVariable UUID franchiseId){
        try{
            ArrayList<CarwashDTO> carwashes = service.getByFranchiseId(franchiseId);
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
