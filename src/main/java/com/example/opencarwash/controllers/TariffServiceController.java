package com.example.opencarwash.controllers;

import com.example.opencarwash.DTOs.carwashService.CarwashServiceDTO;
import com.example.opencarwash.DTOs.tariffService.TariffServiceCreationDTO;
import com.example.opencarwash.DTOs.tariffService.TariffServiceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@PostMapping
@RequestMapping("/tariffService")
public class TariffServiceController {

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody TariffServiceCreationDTO dto){
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
    public ResponseEntity<TariffServiceDTO> getById(@PathVariable UUID id){
        try{
            TariffServiceDTO tariffService = service.getById(id);
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
