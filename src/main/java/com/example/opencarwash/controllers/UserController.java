package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.user.PhoneNumberDTO;
import com.example.opencarwash.dtos.user.RoleDTO;
import com.example.opencarwash.dtos.user.UserDTO;
import com.example.opencarwash.services.UserService;
import com.example.opencarwash.utils.dtomappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
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
            UserDTO user = UserMapper.mapToDTO(service.findById(id));

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

    @PostMapping("/picture")
    public ResponseEntity<HttpStatus> setPicture(@RequestParam String userId,
                                                 @RequestPart("content")MultipartFile picture){
        try{
            service.setPicture(userId, picture);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IOException | NullPointerException e){
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "picture/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPicture(@PathVariable String userId){
        try{
            byte[] picture = service.getPicture(userId);
            return new ResponseEntity<>(picture, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
