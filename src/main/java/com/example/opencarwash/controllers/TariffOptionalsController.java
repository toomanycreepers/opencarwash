package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.carwashService.CarwashServiceDTO;
import com.example.opencarwash.dtos.tariffOptional.TariffOptionalCreationDTO;
import com.example.opencarwash.dtos.tariffOptional.TariffOptionalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RequestMapping("/tariffOptionals")
public class TariffOptionalsController {

    @Autowired
    private TariffOptionalsService service;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody TariffOptionalCreationDTO dto){
        try{
            service.create(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/makeOptional/{id}")
    public ResponseEntity<HttpStatus> makeOptional(@PathVariable UUID id){
        try{
            service.makeOptional(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TariffOptionalDTO> getById(@PathVariable UUID id){
        try{
            TariffOptionalDTO tariffService = service.getById(id);
            return new ResponseEntity<>(tariffService, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/servicesByTariff/{tariffId}")
    @ResponseBody
    public ResponseEntity<ArrayList<CarwashServiceDTO>> getServicesByTariff(@PathVariable UUID tariffId){
        try{
            ArrayList<CarwashServiceDTO> services = service.getServicesByTariff(tariffId);
            return new ResponseEntity<>(services, HttpStatus.OK);
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
