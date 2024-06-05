package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.box.BoxCreationDTO;
import com.example.opencarwash.dtos.box.BoxDTO;
import com.example.opencarwash.dtos.box.NumberDTO;
import com.example.opencarwash.dtos.box.BoxTariffIdDTO;

import com.example.opencarwash.dtos.carwashBox.EmployeeCwBoxDTO;
import com.example.opencarwash.dtos.carwashBox.EmployeeCwBoxRequest;

import com.example.opencarwash.dtos.tariff.FullTariffDTO;

import com.example.opencarwash.entities.Box;
import com.example.opencarwash.services.BoxService;
import com.example.opencarwash.services.BusinessHoursService;
import com.example.opencarwash.services.CarwashBoxService;
import com.example.opencarwash.utils.customExceptions.AbsentFromCollectionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/box")
public class BoxController {

    @Autowired
    private BoxService service;
    @Autowired
    private BusinessHoursService bhService;
    @Autowired
    private CarwashBoxService cwBoxService;

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

    @GetMapping("/{boxId}/tariffs")
    public ResponseEntity<List<FullTariffDTO>> getBoxTariffs(@PathVariable String boxId){
        try{
            return new ResponseEntity<>(service.getTariffsByBox(boxId), HttpStatus.OK);
        }
        catch (NoSuchElementException | IllegalArgumentException e){
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
        catch(IllegalArgumentException | AbsentFromCollectionException | NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<BoxDTO> getById(@PathVariable String id){
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
    public ResponseEntity<List<BoxDTO>> getByCarwashId(@PathVariable String carwashId){
        try{
            List<BoxDTO> boxes = service.getByCarwashId(carwashId);
            return new ResponseEntity<>(boxes, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("getByEmployee")
    @ResponseBody
    public ResponseEntity<List<EmployeeCwBoxDTO>> findByEmployees(@RequestBody EmployeeCwBoxRequest dto){
        try{
            List<EmployeeCwBoxDTO> dtos = cwBoxService.getCarwashBoxesByEmployeeDate(dto);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        catch(ParseException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> remove(@PathVariable String id){
        try{
            service.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
