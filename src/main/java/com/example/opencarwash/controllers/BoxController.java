package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.box.BoxCreationDTO;
import com.example.opencarwash.dtos.box.BoxDTO;
import com.example.opencarwash.dtos.box.NumberDTO;
import com.example.opencarwash.dtos.box.BoxTariffIdDTO;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.services.BoxService;
import com.example.opencarwash.services.BusinessHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/box")
public class BoxController {

    @Autowired
    private BoxService service;
    @Autowired
    private BusinessHoursService bhService;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody BoxCreationDTO dto){
        try{

            Box box = service.addBox(dto);
            bhService.createBaseWeek(box);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/number")
    public ResponseEntity<HttpStatus> updateNumber(@RequestBody NumberDTO dto){
        try{
            service.updateNumber(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addTariff")
    public ResponseEntity<HttpStatus> addTariff(@RequestBody BoxTariffIdDTO dto){
        try{
            service.addTariff(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/removeTariff")
    public ResponseEntity<HttpStatus> removeTariff(@RequestBody BoxTariffIdDTO dto){
        try{
            service.removeTariff(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<BoxDTO> getById(@PathVariable UUID id){
        try{
            BoxDTO box = service.getDTO(id);
            return new ResponseEntity<>(box, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/carwashId/{carwashId}")
    @ResponseBody
    public ResponseEntity<List<BoxDTO>> getByCarwashId(@PathVariable UUID carwashId){
        try{
            List<BoxDTO> boxes = service.getByCarwashId(carwashId);
            return new ResponseEntity<>(boxes, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> remove(@PathVariable UUID id){
        try{
            service.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
