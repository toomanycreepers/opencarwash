package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.carwashService.CwServiceDescriptionDTO;
import com.example.opencarwash.dtos.carwashService.CwServiceCreationDTO;
import com.example.opencarwash.dtos.carwashService.*;
import com.example.opencarwash.services.CwServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carwashService")
public class CarwashServiceController {

    @Autowired
    private CwServiceService service;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody CwServiceCreationDTO dto){
        try{
            service.addCwService(dto);
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
    public ResponseEntity<HttpStatus> updateDescription(@RequestBody CwServiceDescriptionDTO dto){
        try{
            service.changeDescription(dto);
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
    public ResponseEntity<CwServiceDTO> getById(@PathVariable String id){
        try{
            CwServiceDTO carwashService = service.getDTOById(id);
            return new ResponseEntity<>(carwashService, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cw/{carwashId}")
    @ResponseBody
    public ResponseEntity<List<CwServiceDTO>> getAllByCarwashId(@PathVariable String carwashId){
        try{
            List<CwServiceDTO> services = service.getByCarwashId(carwashId);
            return new ResponseEntity<>(services, HttpStatus.OK);
        }
        catch (Exception e){
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
