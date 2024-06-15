package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.order.*;
import com.example.opencarwash.services.OrderService;
import com.example.opencarwash.utils.customExceptions.IllegalStatusMutationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody OrderCreationDTO dto){
        try{
            service.create(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/state")
    public ResponseEntity<HttpStatus> updateState(@RequestBody StateDTO dto){
        try{
            service.updateState(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IllegalArgumentException | IllegalStatusMutationException | IndexOutOfBoundsException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/rating")
    public ResponseEntity<HttpStatus> setRating(@RequestBody RatingDTO dto){
        try{
            service.setRating(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/feedback")
    public ResponseEntity<HttpStatus> setFeedback(@RequestBody FeedbackDTO dto){
        try{
            service.setFeedback(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/removeFeedback/{orderId}")
    public ResponseEntity<HttpStatus> removeFeedback(@PathVariable String id){
        try{
            service.removeFeedback(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//TODO
/*    @PutMapping("/addPhoto")
    public ResponseEntity<HttpStatus> addPhoto(@RequestBody PhotoDTO dto){
        try{
            service.addPhoto(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/
//TODO
/*    @PutMapping("/removePhoto")
    public ResponseEntity<HttpStatus> removePhoto(@RequestBody PhotoDTO dto){
        try{
            service.removePhoto(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/
//TODO
/*    @GetMapping("/photos/{id}")
    @ResponseBody
    public ResponseEntity<ArrayList<PhotoDTO>> getPhotos(@PathVariable UUID id){
        try{
            ArrayList<PhotoDTO> photos = service.getPhotos(id);
            return new ResponseEntity<>(photos, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<OrderWithServicesDTO> getById(@PathVariable String id){
        try{
            OrderWithServicesDTO order = service.getDTOById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/box/{boxId}")
    @ResponseBody
    public ResponseEntity<List<OrderWithServicesDTO>> getByBoxId(@PathVariable String boxId){
        try{
            List<OrderWithServicesDTO> orders = service.getByBoxId(boxId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history/{boxId}")
    @ResponseBody
    public ResponseEntity<List<OrderWithServicesDTO>> getBoxHistory(@PathVariable String boxId){
        try{
            List<OrderWithServicesDTO> orders = service.getBoxHistory(boxId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dateBox")
    @ResponseBody
    public ResponseEntity<List<OrderWithServicesDTO>> getByBoxDate(@RequestBody DateBoxDTO dto){
        try{
            List<OrderWithServicesDTO> orders = service.getByDateBox(dto);
            return new ResponseEntity<>(orders, HttpStatus.OK);
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

    @PostMapping("/dateBoxWithTime")
    @ResponseBody
    public ResponseEntity<List<OrderStartEndTimeDTO>> getByBoxDateWithTime(@RequestBody DateBoxDTO dto){
        // try{
            List<OrderStartEndTimeDTO> dtos = service.getByDateBoxWithTime(dto);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        // }
        // catch(NoSuchElementException e){
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        // catch(IllegalArgumentException | DateTimeParseException e){
        //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }
    }
}
