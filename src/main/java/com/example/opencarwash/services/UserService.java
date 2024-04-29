package com.example.opencarwash.services;

import com.example.opencarwash.dtos.UserCreationDTO;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.repositories.UserRepository;
import com.example.opencarwash.utils.dtomappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public User findById(UUID id) throws NoSuchElementException {
        return repo.findById(id).orElseThrow(
                () -> new NoSuchElementException("No such user exists."));
    }

    public boolean setPicture(UUID userId, MultipartFile picture) throws
            NoSuchElementException{
        User user = findById(userId);
        if (picture.getContentType() == null){
            return false;
        }
        try {
            byte[] picBytes = picture.getBytes();
            user.setPicture(picBytes);
            return true;
        }
        catch (IOException ex){
            return false;
        }
    }

    public void addUser(UserCreationDTO dto){
        User user = UserMapper.mapFromDTO(dto);
        repo.save(user);
    }
}
