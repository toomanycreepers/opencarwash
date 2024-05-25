package com.example.opencarwash.services;

import com.example.opencarwash.dtos.carwashService.*;
import com.example.opencarwash.entities.Carwash;
import com.example.opencarwash.entities.CwService;
import com.example.opencarwash.repositories.CwServiceRepository;
import com.example.opencarwash.utils.dtomappers.CwServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CwServiceService {
    @Autowired
    private CwServiceRepository repo;
    @Autowired
    private CarwashService carwashService;

    public CwService findById(UUID id) throws NoSuchElementException{
        return repo.findById(id).orElseThrow(
                () -> new NoSuchElementException("Incorrect service Id provided.")
        );
    }

    public void addCwService(CwServiceCreationDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException{
        Carwash cw = carwashService.findById(UUID.fromString(dto.carwashId));
        CwService cwService = CwServiceMapper.mapFromDTO(dto,cw);
        repo.save(cwService);
    }

    public void changeDescription(CwServiceDescriptionDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException{
        CwService service = parseAndFindById(dto.id);
        service.setDescription(dto.newDescription);
        repo.save(service);
    }

    public void updateName(NameDTO dto){
        CwService service = parseAndFindById(dto.cwServiceId);
        service.setDescription(dto.name);
        repo.save(service);
    }

    public void updatePrice(PriceDTO dto){
        CwService service = parseAndFindById(dto.cwServiceId);
        service.setPrice(dto.price);
        repo.save(service);
    }

    public void updateDuration(DurationDTO dto){
        CwService service = parseAndFindById(dto.cwServiceId);
        service.setDuration(dto.duration.shortValue());
        repo.save(service);
    }

    public CwServiceDTO getDTOById(UUID id){
        CwService service = findById(id);
        return CwServiceMapper.mapToDTO(service);
    }

    public void remove(UUID id){
        repo.deleteById(id);
    }

    public List<CwServiceDTO> getByCarwashId(UUID id){
        List<CwService> services = repo.findAllByCarwashId(id);
        List<CwServiceDTO> dtos = new ArrayList<>();
        for (CwService s : services){
            dtos.add(CwServiceMapper.mapToDTO(s));
        }
        return dtos;
    }

    private CwService parseAndFindById(String maybeUUID) throws
            IllegalArgumentException,
            NoSuchElementException {
        UUID id = UUID.fromString(maybeUUID);
        return findById(id);
    }
}
