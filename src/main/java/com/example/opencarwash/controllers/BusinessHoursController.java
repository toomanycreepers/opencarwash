package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.businessHours.BusinessHoursDTO;
import com.example.opencarwash.dtos.businessHours.MassUpdateDTO;
import com.example.opencarwash.dtos.businessHours.OpenClosingTimeDTO;
import com.example.opencarwash.services.BusinessHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/bHrs")
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

    @PutMapping("/close/{boxId}")
    public ResponseEntity<HttpStatus> close(@PathVariable String boxId){
        try{
            service.changeCarwashClosedStatus(boxId,true);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/open/{boxId}")
    public ResponseEntity<HttpStatus> open(@PathVariable String boxId){
        try{
            service.changeCarwashClosedStatus(boxId,false);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<BusinessHoursDTO> getById(@PathVariable String id){
        try{
            BusinessHoursDTO businessHours = service.getDTOById(id);
            return new ResponseEntity<>(businessHours, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/box/{boxId}")
    @ResponseBody
    public ResponseEntity<Set<BusinessHoursDTO>> getByBoxId(@PathVariable String boxId){
        try{
            Set<BusinessHoursDTO> businessHours = service.getByBoxId(boxId);
            return new ResponseEntity<>(businessHours, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update/box")
    public ResponseEntity<HttpStatus> updateWorkingWeekOfBox(@RequestBody MassUpdateDTO dto){
        try{
            service.updateAllByBox(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (DateTimeParseException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> remove(@PathVariable String id){
        try{
            service.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
