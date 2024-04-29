package com.example.opencarwash.services;

import com.example.opencarwash.dtos.CwServiceCreationDTO;
import com.example.opencarwash.dtos.CwServiceDescriptionDTO;
import com.example.opencarwash.entities.CwService;
import com.example.opencarwash.repositories.CwServiceRepository;
import com.example.opencarwash.utils.dtomappers.CwServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CwServiceService {
    @Autowired
    private CwServiceRepository repo;

    public CwService findById(UUID id) throws NoSuchElementException{
        return repo.findById(id).orElseThrow(
                () -> new NoSuchElementException("Incorrect service Id provided.")
        );
    }

    public void addCwService(CwServiceCreationDTO dto){
        CwService cwService = CwServiceMapper.mapFromDTO(dto);
        repo.save(cwService);
    }

    public void changeDescription(CwServiceDescriptionDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException{
        UUID id = UUID.fromString(dto.id);
        CwService service = findById(id);
        service.setDescription(dto.newDescription);
        repo.save(service);
    }
}
