package com.example.opencarwash.services;

import com.example.opencarwash.dtos.tariff.*;
import com.example.opencarwash.entities.CwService;
import com.example.opencarwash.entities.Tariff;
import com.example.opencarwash.entities.TariffOptionals;
import com.example.opencarwash.repositories.TariffOptionalsRepository;
import com.example.opencarwash.repositories.TariffRepository;
import com.example.opencarwash.utils.Tuple2;
import com.example.opencarwash.utils.dtomappers.TariffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TariffService {
    @Autowired
    private TariffRepository repo;
    @Autowired
    private CwServiceService cwSService;
    @Autowired
    private TariffOptionalsRepository optionalsRepo;

    public Tariff findById(UUID id) throws  NoSuchElementException{
        return repo.findById(id).orElseThrow(
                () -> new NoSuchElementException("Incorrect tariff id provided.")
        );
    }

    public void addTariff(TariffCreationDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException{
        Tariff tariff = new Tariff(dto.name, dto.description);
        repo.save(tariff);
        Set<TariffOptionals> servicesToAdd = new HashSet<>(dto.serviceIds.size());
        for (Tuple2<String,Boolean> tuple: dto.serviceIds){
            UUID serviceId = UUID.fromString(tuple.elem1);
            CwService service = cwSService.findById(serviceId);
            servicesToAdd.add(new TariffOptionals(tuple.elem2,tariff,service));
        }
        optionalsRepo.saveAll(servicesToAdd);
    }

    public void remove(UUID id){
        repo.deleteById(id);
    }

    public void updateDescription(DescriptionDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        Tariff tariff = findById(UUID.fromString(dto.tariffId));
        tariff.setDescription(dto.description);
        repo.save(tariff);
    }

    public void updateBuffertime(BuffertimeDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        Tariff tariff = findById(UUID.fromString(dto.tariffId));
        tariff.setBufferTime(dto.buffertime.shortValue());
        repo.save(tariff);
    }

    public void updateName(NameDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        Tariff tariff = findById(UUID.fromString(dto.tariffId));
        tariff.setName(dto.name);
        repo.save(tariff);
    }

    public void addService(ServiceDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException{
        Tariff tariff = findById(UUID.fromString(dto.tariffId));
        CwService service = cwSService.findById(UUID.fromString(dto.serviceId));
        optionalsRepo.save(new TariffOptionals(dto.isOptional, tariff, service));
    }

    public void removeService(ServiceDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException{
        UUID tariffId = UUID.fromString(dto.tariffId);
        UUID serviceId = UUID.fromString(dto.serviceId);
        optionalsRepo.delete(optionalsRepo.findByTariffIdAndServiceId(tariffId,serviceId).orElseThrow(
                () -> new NoSuchElementException("Service not provided in this tariff.")));
    }

    public void updateCommForEmployees(CommForEmployeesDTO dto){
        Tariff tariff = findById(UUID.fromString(dto.tariffId));
        tariff.setCommentForEmployees(dto.commentForEmployees);
        repo.save(tariff);
    }

    public TariffDTO getDTOById(UUID id){
        Tariff tariff = findById(id);
        return TariffMapper.mapToDTO(tariff);
    }
}