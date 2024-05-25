package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.tariff.*;
import com.example.opencarwash.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tariff")
public class TariffController {

    @Autowired
    private TariffService service;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody TariffCreationDTO dto){
        try{
            service.addTariff(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
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

    @PutMapping("/buffertime")
    public ResponseEntity<HttpStatus> updateBuffertime(@RequestBody BuffertimeDTO dto){
        try{
            service.updateBuffertime(dto);
            return new ResponseEntity<>(HttpStatus.OK);
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

    @PutMapping("/addService")
    public ResponseEntity<HttpStatus> addService(@RequestBody ServiceDTO dto){
        try{
            service.addService(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/removeService")
    public ResponseEntity<HttpStatus> removeService(@RequestBody ServiceDTO dto){
        try{
            service.removeService(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/comm")
    public ResponseEntity<HttpStatus> updateCommForEmployees(@RequestBody CommForEmployeesDTO dto){
        try{
            service.updateCommForEmployees(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TariffDTO> getById(@PathVariable UUID id){
        try{
            TariffDTO tariff = service.getDTOById(id);
            return new ResponseEntity<>(tariff, HttpStatus.OK);
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
