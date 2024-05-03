package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.businessHours.BusinessHoursDTO;
import com.example.opencarwash.dtos.businessHours.OpenClosingTimeDTO;
import com.example.opencarwash.services.BusinessHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/busHours")
public class BusinessHoursController {

    @Autowired
    private BusinessHoursService service;

    @PutMapping("/time")
    public ResponseEntity<HttpStatus> updateTime(@RequestBody OpenClosingTimeDTO dto){
        try {
            service.updateTime(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/close/{id}")
    public ResponseEntity<HttpStatus> close(@PathVariable UUID id){
        try{
            service.close(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/open/{id}")
    public ResponseEntity<HttpStatus> open(@PathVariable UUID id){
        try{
            service.open(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<BusinessHoursDTO> getById(@PathVariable UUID id){
        try{
            BusinessHoursDTO businessHours = service.getDTOById(id);
            return new ResponseEntity<>(businessHours, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/box/{id}")
    @ResponseBody
    public ResponseEntity<ArrayList<BusinessHoursDTO>> getByBoxId(@PathVariable UUID boxId){
        try{
            ArrayList<BusinessHoursDTO> businessHourses = service.getByBoxId(boxId);
            return new ResponseEntity<>(businessHourses, HttpStatus.OK);
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
