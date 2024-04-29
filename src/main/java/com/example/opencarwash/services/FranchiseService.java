package com.example.opencarwash.services;

import com.example.opencarwash.dtos.FranchiseCreationDTO;
import com.example.opencarwash.entities.Franchise;
import com.example.opencarwash.repositories.FranchiseRepository;
import com.example.opencarwash.utils.dtomappers.FranchiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FranchiseService {
    @Autowired
    private FranchiseRepository repo;

    public Franchise findById(UUID franchiseId) throws NoSuchElementException{
        return repo.findById(franchiseId).orElseThrow(
                () -> new NoSuchElementException("Incorrect franchise Id provided."));
    }
}
