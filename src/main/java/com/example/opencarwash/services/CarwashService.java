package com.example.opencarwash.services;

import com.example.opencarwash.dtos.CarwashCreationDTO;
import com.example.opencarwash.entities.Carwash;
import com.example.opencarwash.entities.Franchise;
import com.example.opencarwash.repositories.CarwashRepository;
import com.example.opencarwash.utils.dtomappers.CarwashMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CarwashService {
    @Autowired
    private CarwashRepository repo;
    @Autowired
    private FranchiseService franchiseService;

    public Carwash findById(UUID cwId) throws NoSuchElementException{
        return repo.findById(cwId).orElseThrow(() -> new NoSuchElementException("Invalid carwash Id."));
    }

    public void addCarwash(CarwashCreationDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException{
        UUID franchiseId = UUID.fromString(dto.franchise_id);
        Franchise franchise = franchiseService.findById(franchiseId);
        Carwash carwash = CarwashMapper.mapFromCarwashDTO(dto,franchise);
        repo.save(carwash);
    }
}
