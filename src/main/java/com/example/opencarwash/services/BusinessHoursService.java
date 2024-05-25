package com.example.opencarwash.services;

import com.example.opencarwash.dtos.businessHours.BusinessHoursDTO;
import com.example.opencarwash.dtos.businessHours.OpenClosingTimeDTO;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.entities.BusinessHours;
import com.example.opencarwash.repositories.BusinessHoursRepository;
import com.example.opencarwash.utils.dtomappers.BusinessHoursMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Service
public class BusinessHoursService {
    @Autowired
    private BusinessHoursRepository repo;

    public BusinessHours findById(String id) throws NoSuchElementException{
        return repo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NoSuchElementException("No working time under this id."));
    }

    public BusinessHours findBusinessHours(String boxId, Integer weekDay) throws NoSuchElementException {
        return repo.findBoxWorkDay(UUID.fromString(boxId),weekDay).orElseThrow(
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
        String bhId = dto.businessHoursId;
        BusinessHours bh = findById(bhId);
        LocalTime newOpenTime = LocalTime.parse(dto.openingTime);
        LocalTime newClosingTime = LocalTime.parse(dto.openingTime);
        bh.setOpeningTime(newOpenTime);
        bh.setClosingTime(newClosingTime);
        repo.save(bh);
    }

    public void changeCarwashClosedStatus(String boxId, boolean isClosed){
        var workdays = repo.findAllByBoxId(UUID.fromString(boxId));
        for (BusinessHours bh : workdays){
            bh.setIsClosed(isClosed);
        }
        repo.saveAll(workdays);
    }

    public BusinessHoursDTO getDTOById(String id) throws NoSuchElementException{
        BusinessHours bh = findById(id);
        return BusinessHoursMapper.mapToBusinessHoursDTO(bh);
    }

    public void remove(UUID id) throws NoSuchElementException{
        if (repo.existsById(id)){
            repo.deleteById(id);
            return;
        }
        throw new NoSuchElementException("No element by that id.");
    }

    public Set<BusinessHoursDTO> getByBoxId(String boxId){
        Set<BusinessHours> entitySet = repo.findAllByBoxId(UUID.fromString(boxId));
        Set<BusinessHoursDTO> dtoSet = new HashSet<>();
        for (BusinessHours entity : entitySet){
            BusinessHoursDTO dto = BusinessHoursMapper.mapToBusinessHoursDTO(entity);
            dtoSet.add(dto);
        }
        return dtoSet;
    }
}
