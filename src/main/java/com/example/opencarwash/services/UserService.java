package com.example.opencarwash.services;

import com.example.opencarwash.dtos.user.PhoneNumberDTO;
import com.example.opencarwash.dtos.user.RoleDTO;
import com.example.opencarwash.dtos.user.UserDTO;
import com.example.opencarwash.entities.Role;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.repositories.RoleRepository;
import com.example.opencarwash.repositories.UserRepository;
import com.example.opencarwash.utils.customExceptions.AlreadyPresentException;
import com.example.opencarwash.utils.dtomappers.RoleMapper;
import com.example.opencarwash.utils.dtomappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    public User findById(String id) throws NoSuchElementException {
        User user = userRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NoSuchElementException("No such user exists."));
        return user;
    }

    public UserDTO findByPhoneNumber(String phoneNumber) throws NoSuchElementException {
        User user = userRepo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NoSuchElementException("No such user exists."));
        return UserMapper.mapToDTO(user);
    }

    public void removeById(String id) throws NoSuchElementException {
        UUID userId = UUID.fromString(id);
        if (!userRepo.existsById(userId)) {
            throw new NoSuchElementException();
        }
        userRepo.deleteById(userId);
    }

    public void updatePhoneNumber(PhoneNumberDTO dto) throws AlreadyPresentException, NoSuchElementException {
        if (userRepo.existsByPhoneNumber(dto.phoneNumber)) {
            throw new AlreadyPresentException("User with specified phone number already presents");
        }

        User user = userRepo.findById(UUID.fromString(dto.userId)).orElseThrow(
                () -> new NoSuchElementException("User with specified id does not exists")
        );
        user.setPhoneNumber(dto.phoneNumber);
        userRepo.save(user);
    }

    public void addRole(RoleDTO dto) throws NoSuchElementException, IllegalArgumentException{
        User user = userRepo.findById(UUID.fromString(dto.userId)).orElseThrow(
                () -> new NoSuchElementException("User does not exists")
        );

        Role role = roleRepo.findByName(RoleMapper.numberToRoleName(dto.roleNumber)).orElseThrow(
                () -> new NoSuchElementException("DB doesn't contains this role")
        );

        user.getUserRoles().add(role);
        userRepo.save(user);
    }

    public void removeRole(RoleDTO dto) throws NoSuchElementException{
        User user = userRepo.findById(UUID.fromString(dto.userId)).orElseThrow(
                () -> new NoSuchElementException("User does not exists")
        );

        Predicate<Role> rolePredicate = role ->
                role.getName().equals(RoleMapper.numberToRoleName(dto.roleNumber));

        if(user.getUserRoles().stream().anyMatch(rolePredicate)){
            user.getUserRoles().removeIf(rolePredicate);
            userRepo.save(user);
        }

        else{
            throw new NoSuchElementException("User doesn't have role with number");
        }
    }

    public void setPicture(String userId, MultipartFile picture) throws
            NoSuchElementException,
            NullPointerException,
            IOException {
        User user = userRepo.findById(UUID.fromString(userId)).orElseThrow(
                () -> new NoSuchElementException("User with specified id does not exists")
        );
        if (picture.getContentType() == null) {
            throw new NullPointerException("Empty picture content");
        }

        byte[] picBytes = picture.getBytes();
        user.setPicture(picBytes);
        userRepo.save(user);
    }

    public byte[] getPicture(String userId) throws
            NoSuchElementException,
            IOException{
        User user = userRepo.findById(UUID.fromString(userId)).orElseThrow(
                () -> new NoSuchElementException("User with specified id does not exists")
        );

        if(user.getPicture() == null){
            return new ClassPathResource("src/main/resources/img/default avatar.jpg").getContentAsByteArray();
        }

        return user.getPicture();
    }
}
