package com.example.opencarwash.services;

import com.example.opencarwash.dtos.businessHours.BusinessHoursDTO;
import com.example.opencarwash.dtos.businessHours.OpenClosingTimeDTO;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.entities.BusinessHours;
import com.example.opencarwash.repositories.BusinessHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
public class BusinessHoursService {
    @Autowired
    private BusinessHoursRepository repo;

    public BusinessHours findById(UUID id) throws NoSuchElementException{
        return repo.findById(id).orElseThrow(
                () -> new NoSuchElementException("No working time under this id."));
    }

    public BusinessHours findBusinessHours(UUID boxId, Integer weekDay) throws NoSuchElementException {
        return repo.findBoxWorkDay(boxId,weekDay).orElseThrow(
                () -> new NoSuchElementException("Box with provided Id does not exist."));
    }

    public void createBaseWeek(Box box){
        Set<BusinessHours> week = new HashSet<>();
        for (int i=1;i<=7;i++){
            week.add(new BusinessHours(LocalTime.MIN,LocalTime.MIN,i,true,box));
        }
        repo.saveAll(week);
    }

    public void updateTime(OpenClosingTimeDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException{
        UUID bhId = UUID.fromString(dto.businessHoursId);
        BusinessHours bh = findById(bhId);
        LocalTime newOpenTime = LocalTime.parse(dto.openingTime);
        LocalTime newClosingTime = LocalTime.parse(dto.openingTime);
        bh.setOpeningTime(newOpenTime);
        bh.setClosingTime(newClosingTime);
        repo.save(bh);
    }
}
