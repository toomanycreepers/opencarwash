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
import java.util.stream.Collectors;

@Service
public class TariffService {
    @Autowired
    private TariffRepository repo;
    @Autowired
    private CwServiceService cwSService;
    @Autowired
    private TariffOptionalsRepository optionalsRepo;

    public Tariff findById(String id) throws  NoSuchElementException{
        return repo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NoSuchElementException("Incorrect tariff id provided.")
        );
    }

    public String addTariff(TariffCreationDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException{
        Tariff tariff = new Tariff(dto.name, dto.description);
        UUID id = repo.save(tariff).getId();

        Set<TariffOptionals> servicesToAdd = new HashSet<>(dto.serviceIds.size());
        for (Tuple2<String,Boolean> tuple: dto.serviceIds){
            CwService service = cwSService.findById(tuple.elem1);
            servicesToAdd.add(new TariffOptionals(tuple.elem2,tariff,service));
        }
        optionalsRepo.saveAll(servicesToAdd);
        return id.toString();
    }

    public void remove(String id) throws IllegalArgumentException {
        UUID tariffUUID = UUID.fromString(id);
        if (repo.existsById(tariffUUID)) {
            repo.deleteById(tariffUUID);
            return;
        }
        throw new NoSuchElementException("Nothing to delete.");
    }

    public void update(TariffDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException{
        Tariff tariff = findById(dto.id);

        tariff.setName(dto.name);
        tariff.setDescription(dto.description);
        tariff.setBufferTime(dto.BufferTime);
        tariff.setCommentForEmployees(dto.commentForEmployees);
        repo.save(tariff);

        Set<TariffOptionals> tarOpt = new HashSet<TariffOptionals>();
        List<CwService> cwServices = dto.serviceIds.stream().map(cwSService::findById).toList();
        optionalsRepo.deleteAll(optionalsRepo.findAllByTariffId(tariff.getId()));
        for(CwService cwService : cwServices){
            tarOpt.add(new TariffOptionals(false, tariff, cwService));
        }
        optionalsRepo.saveAll(tarOpt);
    }

    public void updateDescription(DescriptionDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        Tariff tariff = findById(dto.tariffId);
        tariff.setDescription(dto.description);
        repo.save(tariff);
    }

    public void updateBuffertime(BuffertimeDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        Tariff tariff = findById(dto.tariffId);
        tariff.setBufferTime(dto.buffertime.shortValue());
        repo.save(tariff);
    }

    public void updateName(NameDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        Tariff tariff = findById(dto.tariffId);
        tariff.setName(dto.name);
        repo.save(tariff);
    }

    public void addService(ServiceDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException{
        Tariff tariff = findById(dto.tariffId);
        CwService service = cwSService.findById(dto.serviceId);
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
        Tariff tariff = findById(dto.tariffId);
        tariff.setCommentForEmployees(dto.commentForEmployees);
        repo.save(tariff);
    }

    public FullTariffDTO getTariffWithServices(String tariffUUID) throws
            IllegalArgumentException,
            NoSuchElementException{
        Tariff tariff = findById(tariffUUID);
        return TariffMapper.mapToFullDTO(tariff);
    }

    public TariffDTO getDTOById(String uuid){
        Tariff tariff = findById(uuid);
        return TariffMapper.mapToDTO(tariff);
    }

    public FullTariffDTO getFullDTOById(String uuid){
        Tariff tariff = findById(uuid);
        return TariffMapper.mapToFullDTO(tariff);
    }
}
