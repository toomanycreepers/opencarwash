package com.example.opencarwash.controllers;

import com.example.opencarwash.dtos.order.*;
import com.example.opencarwash.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
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
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<OrderDTO> getById(@PathVariable String id){
        try{
            OrderDTO order = service.getDTOById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/box/{boxId}")
    @ResponseBody
    public ResponseEntity<List<OrderDTO>> getByBoxId(@PathVariable String boxId){
        try{
            List<OrderDTO> orders = service.getByBoxId(boxId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dateBox")
    @ResponseBody
    public ResponseEntity<List<OrderDTO>> getByBoxDate(@RequestBody DateBoxDTO dto){
        try{
            List<OrderDTO> orders = service.getByDateBox(dto);
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
}
