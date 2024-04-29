package com.example.opencarwash.services;

import com.example.opencarwash.dtos.TariffCreationDTO;
import com.example.opencarwash.entities.Tariff;
import com.example.opencarwash.repositories.TariffRepository;
import com.example.opencarwash.utils.dtomappers.TariffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TariffService {
    @Autowired
    private TariffRepository repo;

    public Tariff findById(UUID id) throws  NoSuchElementException{
        return repo.findById(id).orElseThrow(
                () -> new NoSuchElementException("Incorrect tariff id provided.")
        );
    }

    public void addTariff(TariffCreationDTO dto){
        Tariff tariff = TariffMapper.mapFromDTO(dto);
        repo.save(tariff);
    }
}
