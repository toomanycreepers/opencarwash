package com.example.opencarwash.services;

import com.example.opencarwash.dtos.BoxCreationDTO;
import com.example.opencarwash.dtos.BoxTimeDTO;
import com.example.opencarwash.dtos.BoxWorkWeekTimeDTO;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.entities.BusinessHours;
import com.example.opencarwash.entities.Carwash;
import com.example.opencarwash.repositories.BoxRepository;
import com.example.opencarwash.repositories.BusinessHoursRepository;
import com.example.opencarwash.utils.dtomappers.BusinessHoursMapper;
import com.example.opencarwash.utils.enums.WorkWeekType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
public class BoxService {
    @Autowired
    private BoxRepository repo;
    @Autowired
    private BusinessHoursRepository timeRepo;
    @Autowired
    private CarwashService cwService;

    public Box findById(UUID id) throws NoSuchElementException {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Invalid box id."));
    }

    public void setOpTimeForWorkWeek(BoxWorkWeekTimeDTO dto) throws NoSuchElementException {
        Box box = findById(dto.boxId);

        int workingDays = box.getWorkWeekType().getDaysWorking();
        Set<BusinessHours> days = new HashSet<>();
        for (int i=1;i<=workingDays;i++){
            days.add(BusinessHoursMapper.mapFromBusinessHoursDTO(dto,box,i));
        }
        timeRepo.saveAll(days);
    }

    public void closeBox(UUID boxId) throws NoSuchElementException {
        changeBoxStatusTo(true, boxId);
    }

    public void openBox(UUID boxId) throws NoSuchElementException {
        changeBoxStatusTo(false, boxId);
    }

    public Box addBox(BoxCreationDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException,
            ArrayIndexOutOfBoundsException {
        WorkWeekType type = WorkWeekType.values()[dto.workWeekType];
        UUID carwashId = UUID.fromString(dto.carwashId);
        Carwash carwash = cwService.findById(carwashId);
        Box box = new Box(dto.number, type, carwash);
        repo.save(box);
        return box;
    }

    public void setOpTimeForDay(BoxTimeDTO dto) throws NoSuchElementException {
        Box box = findById(dto.boxId);
        BusinessHours opTime = BusinessHoursMapper.mapFromBusinessHoursDTO(dto,box);
        timeRepo.save(opTime);
    }

    private void changeBoxStatusTo(boolean isClosed, UUID boxId){
        Box box = findById(boxId);
        for (BusinessHours day:box.getOpTime()) {
            day.setIsClosed(isClosed);
        }
        repo.save(box);
    }
}