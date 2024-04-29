package com.example.opencarwash.controllers;

import com.example.opencarwash.DTOs.box.BoxCreationDTO;
import com.example.opencarwash.DTOs.box.BoxDTO;
import com.example.opencarwash.DTOs.box.NumberDTO;
import com.example.opencarwash.DTOs.box.BoxTariffIdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/box")
public class BoxController {

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody BoxCreationDTO dto){
        try{
            service.create(dto);
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
            BoxDTO box = service.getById(id);
            return new ResponseEntity<>(box, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/carwashId/{carwashId}")
    @ResponseBody
    public ResponseEntity<ArrayList<BoxDTO>> getByCarwashId(@PathVariable UUID carwashId){
        try{
            ArrayList<BoxDTO> boxes = service.getByCarwashId(carwashId);
            return new ResponseEntity<>(boxes, HttpStatus.OK);
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
