package com.example.opencarwash.services;

import com.example.opencarwash.dtos.franchise.FranchiseCreationDTO;
import com.example.opencarwash.dtos.franchise.FranchiseDTO;
import com.example.opencarwash.dtos.franchise.NameDTO;
import com.example.opencarwash.dtos.franchise.OwnerDTO;
import com.example.opencarwash.entities.Franchise;
import com.example.opencarwash.entities.User;
import com.example.opencarwash.repositories.FranchiseRepository;
import com.example.opencarwash.repositories.UserRepository;
import com.example.opencarwash.utils.dtomappers.FranchiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FranchiseService {
    @Autowired
    private FranchiseRepository repo;
    @Autowired
    private UserService userService;

    public Franchise findById(String franchiseId) throws
            NoSuchElementException,
            IllegalArgumentException {
        return repo.findById(UUID.fromString(franchiseId)).orElseThrow(
                () -> new NoSuchElementException("Incorrect franchise Id provided."));
    }

    public void remove(String id) throws IllegalArgumentException {
        UUID franchiseUUID = UUID.fromString(id);
        if (repo.existsById(franchiseUUID)) {
            repo.deleteById(franchiseUUID);
            return;
        }
        throw new NoSuchElementException("Nothing to delete.");
    }

    public void create(FranchiseCreationDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        User owner = userService.findById(dto.ownerId);
        Franchise franchise = FranchiseMapper.mapFromDTO(dto,owner);
        repo.save(franchise);
    }

    public void updateName(NameDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException {
        Franchise franchise = findById(dto.franchiseId);
        franchise.setName(dto.name);
        repo.save(franchise);
    }

    public FranchiseDTO getByName(String name) throws NoSuchElementException{
        Franchise franchise = repo.findByName(name).orElseThrow(
                () -> new NoSuchElementException("Incorrect franchise name provided."));
        return FranchiseMapper.mapToDTO(franchise);
    }

    public void updateOwner(OwnerDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException{
        User owner = userService.findById(dto.ownerId);
        Franchise franchise = findById(dto.franchiseId);
        franchise.setOwner(owner);
        repo.save(franchise);
    }
}
