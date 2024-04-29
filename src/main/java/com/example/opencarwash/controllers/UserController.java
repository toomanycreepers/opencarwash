package com.example.opencarwash.controllers;

import com.example.opencarwash.DTOs.User.PhoneNumberDTO;
import com.example.opencarwash.DTOs.User.RoleDTO;
import com.example.opencarwash.DTOs.User.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @PutMapping("/phone")
    public ResponseEntity<HttpStatus> updatePhonenNumber(@RequestBody PhoneNumberDTO dto){
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
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/removeRole")
    public ResponseEntity<HttpStatus> removeRole(@RequestBody RoleDTO dto){
        try{
            service.removeRole(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> getById(@PathVariable UUID id){
        try{
            UserDTO user = service.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/phone/{phoneNumber}")
    @ResponseBody
    public ResponseEntity<UserDTO> getByPhoneNUmber(@PathVariable String phoneNumber){
        try{
            UserDTO user = service.getByPhoneNumber(phoneNumber);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeById(@PathVariable UUID id){
        try{
            service.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
