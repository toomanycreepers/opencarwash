package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.order.DateBoxDTO;
import com.example.opencarwash.dtos.order.OrderWithServicesDTO;
import com.example.opencarwash.dtos.tariff.*;
import com.example.opencarwash.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/tariff")
public class TariffController {

    @Autowired
    private TariffService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TariffCreationDTO dto){
        try{
            String id = service.addTariff(dto);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody TariffDTO dto){
        try{
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<TariffDTO> getById(@PathVariable String id){
        try{
            TariffDTO tariff = service.getDTOById(id);
            return new ResponseEntity<>(tariff, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/full/{id}")
    @ResponseBody
    public ResponseEntity<FullTariffDTO> getFullById(@PathVariable String id){
        try{
            FullTariffDTO tariff = service.getFullDTOById(id);
            return new ResponseEntity<>(tariff, HttpStatus.OK);
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
