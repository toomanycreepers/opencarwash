package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.user.PhoneNumberDTO;
import com.example.opencarwash.dtos.user.RoleDTO;
import com.example.opencarwash.dtos.user.UserDTO;
import com.example.opencarwash.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PutMapping("/phone")
    public ResponseEntity<HttpStatus> updatePhoneNumber(@RequestBody PhoneNumberDTO dto){
        try{
            service.updatePhoneNumber(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addRole")
    public ResponseEntity<HttpStatus> addRole(@RequestBody RoleDTO dto){
        try{
            service.addRole(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/removeRole")
    public ResponseEntity<HttpStatus> removeRole(@RequestBody RoleDTO dto){
        try{
            service.removeRole(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        try{
            UserDTO user = service.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/phone/{phoneNumber}")
    @ResponseBody
    public ResponseEntity<UserDTO> findByPhoneNUmber(@PathVariable String phoneNumber){
        try{
            UserDTO user = service.findByPhoneNumber(phoneNumber);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeById(@PathVariable String id){
        try{
            service.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
