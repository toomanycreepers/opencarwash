package com.example.opencarwash.services;

import com.example.opencarwash.dtos.businessHours.BoxTimeDTO;
import com.example.opencarwash.dtos.box.BoxWorkWeekTimeDTO;
import com.example.opencarwash.dtos.box.BoxCreationDTO;
import com.example.opencarwash.dtos.box.BoxDTO;
import com.example.opencarwash.dtos.box.BoxTariffIdDTO;
import com.example.opencarwash.dtos.box.NumberDTO;
import com.example.opencarwash.dtos.tariff.FullTariffDTO;
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

    public Box findById(String id) throws
            NoSuchElementException,
            IllegalArgumentException {
        return repo.findById(UUID.fromString(id)).orElseThrow(() -> new NoSuchElementException("Invalid box id."));
    }

    public void setOpTimeForWorkWeek(BoxWorkWeekTimeDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException {
        Box box = findById(dto.boxId);

        int workingDays = box.getWorkWeekType().getDaysWorking();
        Set<BusinessHours> days = new HashSet<>();
        for (int i=1;i<=workingDays;i++){
            days.add(BusinessHoursMapper.mapFromBusinessHoursDTO(dto,box,i));
        }
        timeRepo.saveAll(days);
    }

    public void closeBox(String boxId) throws
            IllegalArgumentException,
            NoSuchElementException {
        changeBoxStatusTo(true, boxId);
    }

    public void openBox(String boxId) throws
            IllegalArgumentException,
            NoSuchElementException {
        changeBoxStatusTo(false, boxId);
    }

    public Box addBox(BoxCreationDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException,
            ArrayIndexOutOfBoundsException {
        WorkWeekType type = WorkWeekType.values()[dto.workWeekType];
        Carwash carwash = cwService.findById(dto.carwashId);
        Box box = new Box(dto.number, type, carwash);
        repo.save(box);
        return box;
    }

    public void setOpTimeForDay(BoxTimeDTO dto) throws
            IllegalArgumentException,
            NoSuchElementException {
        Box box = findById(dto.boxId);
        BusinessHours opTime = BusinessHoursMapper.mapFromBusinessHoursDTO(dto,box);
        timeRepo.save(opTime);
    }

    public void updateNumber(NumberDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException {
        Box box = findById(dto.boxId);
        box.setNumber(dto.number);
        repo.save(box);
    }

    public void addTariff(BoxTariffIdDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException,
            AlreadyPresentException {
        Box box = findById(dto.boxId);
        Tariff tariff = tariffService.findById(dto.tariffId);
        if(!box.addTariff(tariff)){
            throw new AlreadyPresentException("Tariff is already added.");
        }
    }

    public void removeTariff(BoxTariffIdDTO dto) throws
            NoSuchElementException,
            IllegalArgumentException,
            AbsentFromCollectionException {
        Box box = findById(dto.boxId);
        Tariff tariff = tariffService.findById(dto.tariffId);
        if(!box.removeTariff(tariff)){
            throw new AbsentFromCollectionException("No such tariff");
        }
    }

    public BoxDTO getDTO(String boxId) throws NoSuchElementException{
        Box box = findById(boxId);
        return BoxMapper.toDTO(box);
    }

    public List<BoxDTO> getByCarwashId(String carwashId) throws
            IllegalArgumentException {
        UUID carwashUUID = UUID.fromString(carwashId);
        List<Box> boxes = repo.findAllByCarwashId(carwashUUID);
        List<BoxDTO> boxDTOs = new ArrayList<>();
        for (Box box: boxes){
            boxDTOs.add(BoxMapper.toDTO(box));
        }
        return boxDTOs;
    }

    public List<FullTariffDTO> getTariffsByBox(String boxUUID) throws
            NoSuchElementException,
            IllegalArgumentException{
        Box box = findById(boxUUID);
        return box.getTariffs()
                .stream()
                .map(x -> tariffService.getTariffWithServices(x.getId().toString()))
                .toList();
    }

    public void removeById(String id) throws IllegalArgumentException {
        UUID boxId = UUID.fromString(id);
        repo.deleteById(boxId);
    }

    private void changeBoxStatusTo(boolean isClosed, String boxId){
        Box box = findById(boxId);
        for (BusinessHours day:box.getOpTime()) {
            day.setIsClosed(isClosed);
        }
        repo.save(box);
    }
}