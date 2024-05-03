package com.example.opencarwash.services;

import com.example.opencarwash.dtos.businessHours.BoxTimeDTO;
import com.example.opencarwash.dtos.BoxWorkWeekTimeDTO;
import com.example.opencarwash.dtos.box.BoxCreationDTO;
import com.example.opencarwash.dtos.box.BoxDTO;
import com.example.opencarwash.dtos.box.BoxTariffIdDTO;
import com.example.opencarwash.dtos.box.NumberDTO;
import com.example.opencarwash.entities.Box;
import com.example.opencarwash.entities.BusinessHours;
import com.example.opencarwash.entities.Carwash;
import com.example.opencarwash.entities.Tariff;
import com.example.opencarwash.repositories.BoxRepository;
import com.example.opencarwash.repositories.BusinessHoursRepository;
import com.example.opencarwash.utils.customExceptions.AbsentFromCollectionException;
import com.example.opencarwash.utils.customExceptions.AlreadyPresentException;
import com.example.opencarwash.utils.dtomappers.BoxMapper;
import com.example.opencarwash.utils.dtomappers.BusinessHoursMapper;
import com.example.opencarwash.utils.enums.WorkWeekType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoxService {
    @Autowired
    private BoxRepository repo;
    @Autowired
    private BusinessHoursRepository timeRepo;
    @Autowired
    private TariffService tariffService;
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

    public void updateNumber(NumberDTO dto) throws NoSuchElementException {
        Box box = findById(dto.boxId);
        box.setNumber(dto.number);
        repo.save(box);
    }

    public void addTariff(BoxTariffIdDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException,
            AlreadyPresentException{
        UUID boxId = UUID.fromString(dto.boxId);
        UUID tId = UUID.fromString(dto.tariffId);
        Box box = findById(boxId);
        Tariff tariff = tariffService.findById(tId);
        if(!box.addTariff(tariff)){
            throw new AlreadyPresentException("Tariff is already added.");
        }
    }

    public void removeTariff(BoxTariffIdDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException,
            AbsentFromCollectionException{
        UUID boxId = UUID.fromString(dto.boxId);
        UUID tId = UUID.fromString(dto.tariffId);
        Box box = findById(boxId);
        Tariff tariff = tariffService.findById(tId);
        if(!box.removeTariff(tariff)){
            throw new AbsentFromCollectionException("No such tariff");
        }
    }

    public BoxDTO getDTO(UUID boxId) throws NoSuchElementException{
        Box box = findById(boxId);
        return BoxMapper.toDTO(box);
    }

    public List<BoxDTO> getByCarwashId(UUID carwashId){
        List<Box> boxes = repo.findAllByCarwashId(carwashId);
        List<BoxDTO> boxDTOs = new ArrayList<>();
        for (Box box: boxes){
            boxDTOs.add(BoxMapper.toDTO(box));
        }
        return boxDTOs;
    }

    public void removeById(UUID id){
        repo.deleteById(id);
    }

    private void changeBoxStatusTo(boolean isClosed, UUID boxId){
        Box box = findById(boxId);
        for (BusinessHours day:box.getOpTime()) {
            day.setIsClosed(isClosed);
        }
        repo.save(box);
    }
}