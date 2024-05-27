package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.franchise.FranchiseCreationDTO;
import com.example.opencarwash.dtos.franchise.FranchiseDTO;
import com.example.opencarwash.dtos.franchise.NameDTO;
import com.example.opencarwash.dtos.franchise.OwnerDTO;
import com.example.opencarwash.services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseService service;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody FranchiseCreationDTO dto){
        try{
            service.create(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<FranchiseDTO> getByName(@PathVariable String name){
        try{
            FranchiseDTO franchise = service.getByName(name);
            return new ResponseEntity<>(franchise, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/owner")
    public ResponseEntity<HttpStatus> updateOwner(@RequestBody OwnerDTO dto){
        try{
            service.updateOwner(dto);
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
