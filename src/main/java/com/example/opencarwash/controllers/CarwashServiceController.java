package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.carwash.CarwashCreationDTO;
import com.example.opencarwash.dtos.carwashService.*;
import com.example.opencarwash.services.CwServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carwashService")
public class CarwashServiceController {

    @Autowired
    private CwServiceService service;

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

    @PutMapping("/name")
    public ResponseEntity<HttpStatus> updateName(@RequestBody NameDTO dto){
        try{
            service.updateName(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/price")
    public ResponseEntity<HttpStatus> updatePrice(@RequestBody PriceDTO dto){
        try{
            service.updatePrice(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/description")
    public ResponseEntity<HttpStatus> updateDescription(@RequestBody DescriptionDTO dto){
        try{
            service.updateDescription(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/duration")
    public ResponseEntity<HttpStatus> updateDuration(@RequestBody DurationDTO dto){
        try{
            service.updateDuration(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CarwashServiceDTO> getById(@PathVariable UUID id){
        try{
            CarwashServiceDTO carwashService = service.getById(id);
            return new ResponseEntity<>(carwashService, HttpStatus.OK);
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
