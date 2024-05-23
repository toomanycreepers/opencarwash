package com.example.opencarwash.services;

import com.example.opencarwash.dtos.UserCreationDTO;
import com.example.opencarwash.dtos.user.PhoneNumberDTO;
import com.example.opencarwash.dtos.user.RoleDTO;
import com.example.opencarwash.dtos.user.UserDTO;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.repositories.UserRepository;
import com.example.opencarwash.utils.customExceptions.AlreadyPresentException;
import com.example.opencarwash.utils.dtomappers.RoleMapper;
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

    public UserDTO findById(String id) throws NoSuchElementException {
        User user = repo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NoSuchElementException("No such user exists."));
        return UserMapper.mapToDTO(user);
    }

    public UserDTO findByPhoneNumber(String phoneNumber) throws NoSuchElementException {
        User user = repo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NoSuchElementException("No such user exists."));
        return UserMapper.mapToDTO(user);
    }

    public void removeById(String id) throws NoSuchElementException {
        UUID userId = UUID.fromString(id);
        if (!repo.existsById(userId)) {
            throw new NoSuchElementException();
        }
        repo.deleteById(userId);
    }

    public void updatePhoneNumber(PhoneNumberDTO dto) throws AlreadyPresentException, NoSuchElementException {
        if (repo.existsByPhoneNumber(dto.phoneNumber)) {
            throw new AlreadyPresentException("User with specified phone number already presents");
        }

        User user = repo.findById(UUID.fromString(dto.userId)).orElseThrow(
                () -> new NoSuchElementException("User with specified id does not exists")
        );
        user.setPhoneNumber(dto.phoneNumber);
        repo.save(user);
    }


    public void setPicture(String userId, MultipartFile picture) throws
            NoSuchElementException, NullPointerException, IOException {
        User user = repo.findById(UUID.fromString(userId)).orElseThrow(
                () -> new NoSuchElementException("User with specified id does not exists")
        );
        if (picture.getContentType() == null) {
            throw new NullPointerException("Empty picture content");
        }

        byte[] picBytes = picture.getBytes();
        user.setPicture(picBytes);
        repo.save(user);
    }

    public void addRole(RoleDTO dto) throws NoSuchElementException, IllegalArgumentException{
        User user = repo.findById(UUID.fromString(dto.userId)).orElseThrow(
                () -> new NoSuchElementException("User does not exists")
        );

        user.getUserRoles().add(RoleMapper.numberToRole(dto.roleNumber));
        repo.save(user);
    }

    public void removeRole(RoleDTO dto) throws NoSuchElementException{
        User user = repo.findById(UUID.fromString(dto.userId)).orElseThrow(
                () -> new NoSuchElementException("User does not exists")
        );

        if(user.getUserRoles().contains(RoleMapper.numberToRole(dto.roleNumber))){
            user.getUserRoles().remove(RoleMapper.numberToRole(dto.roleNumber));
            repo.save(user);
        }

        else{
            throw new NoSuchElementException("User doesn't have role with number");
        }
    }
}
