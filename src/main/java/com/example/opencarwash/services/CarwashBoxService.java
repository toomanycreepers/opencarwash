package com.example.opencarwash.services;

import com.example.opencarwash.dtos.box.BoxDTO;
import com.example.opencarwash.dtos.businessHours.BusinessHoursDTO;
import com.example.opencarwash.dtos.carwash.CarwashDTO;
import com.example.opencarwash.dtos.carwashBox.BoxDescriptionDTO;
import com.example.opencarwash.dtos.carwashBox.EmployeeCwBoxDTO;
import com.example.opencarwash.dtos.carwashBox.EmployeeCwBoxRequest;
import com.example.opencarwash.entities.BusinessHours;
import com.example.opencarwash.utils.dtomappers.BoxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CarwashBoxService {
    @Autowired
    private CarwashService cwService;
    @Autowired
    private BoxService boxService;
    @Autowired
    private BusinessHoursService bhService;

    private Date parseDate(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(strDate);
    }

    private int getWeekdayByDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public List<EmployeeCwBoxDTO> getCarwashBoxesByEmployeeDate(EmployeeCwBoxRequest dto) throws
            ParseException,
            NoSuchElementException{
        List<CarwashDTO> cws = cwService.getByEmployeeId(UUID.fromString(dto.employeeId));
        int weekday = getWeekdayByDate(parseDate(dto.date));
        List<EmployeeCwBoxDTO> res = new ArrayList<EmployeeCwBoxDTO>();

        Predicate<BusinessHoursDTO> bhWeekdayPredicate = bh ->
                bh.weekday.equals(weekday);

        for(CarwashDTO cw : cws){
            List<BoxDTO> boxes = boxService.getByCarwashId(cw.id);
            List<BoxDescriptionDTO> boxDescriptionDTOs = new ArrayList<BoxDescriptionDTO>();

            for(BoxDTO box : boxes){
                boxDescriptionDTOs.add(BoxMapper.mapDTOtoDescriptionDTO(
                        box,
                        bhService.getByBoxId(box.id).stream().filter(bhWeekdayPredicate).findFirst().get().isClosed)
                );
            }

            res.add(new EmployeeCwBoxDTO(
                    cw.id, cw.city, cw.street, cw.building, cw.timeSlot, boxDescriptionDTOs
            ));
        }

        return res;
    }

}
