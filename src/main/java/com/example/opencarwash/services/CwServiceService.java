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

    public CwService findById(String id) throws
            NoSuchElementException,
            IllegalArgumentException {
        return repo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NoSuchElementException("Incorrect service Id provided.")
        );
    }

    public CwService addCwService(CwServiceCreationDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException {
        Carwash cw = carwashService.findById(dto.carwashId);
        CwService cwService = CwServiceMapper.mapFromCreationDTO(dto,cw);
        return repo.save(cwService);
    }

    public void update(CwServiceDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException{
        CwService service = findById(dto.id);
        service.setName(dto.name);
        service.setDescription(dto.description);
        service.setPrice(dto.price);
        service.setDuration(dto.duration.shortValue());
        repo.save(service);
    }
    public void changeDescription(CwServiceDescriptionDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException {
        CwService service = findById(dto.id);
        service.setDescription(dto.newDescription);
        repo.save(service);
    }

    public void updateName(NameDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        CwService service = findById(dto.cwServiceId);
        service.setDescription(dto.name);
        repo.save(service);
    }

    public void updatePrice(PriceDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        CwService service = findById(dto.cwServiceId);
        service.setPrice(dto.price);
        repo.save(service);
    }

    public void updateDuration(DurationDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        CwService service = findById(dto.cwServiceId);
        service.setDuration(dto.duration.shortValue());
        repo.save(service);
    }

    public CwServiceDTO getDTOById(String id) throws
            IllegalArgumentException,
            NoSuchElementException {
        CwService service = findById(id);
        return CwServiceMapper.mapToDTO(service);
    }

    public void remove(String id) {
        UUID cwServiceUUID = UUID.fromString(id);
        if (repo.existsById(cwServiceUUID)) {
            repo.deleteById(cwServiceUUID);
            return;
        }
        throw new NoSuchElementException("Nothing to delete.");
    }

    public List<CwServiceDTO> getByCarwashId(String id) throws
            IllegalArgumentException {
        UUID cwUUID = UUID.fromString(id);
        List<CwService> services = repo.findAllByCarwashId(cwUUID);
        List<CwServiceDTO> dtos = new ArrayList<>();
        for (CwService s : services){
            dtos.add(CwServiceMapper.mapToDTO(s));
        }
        return dtos;
    }
}
