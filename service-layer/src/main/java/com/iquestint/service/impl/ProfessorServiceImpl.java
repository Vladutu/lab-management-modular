package com.iquestint.service.impl;

import com.iquestint.dao.LaboratoryDao;
import com.iquestint.dto.LaboratoryDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceInvalidSemesterException;
import com.iquestint.model.Day;
import com.iquestint.model.Hour;
import com.iquestint.model.Laboratory;
import com.iquestint.service.ProfessorService;
import com.iquestint.utils.WeekCalculator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author vladu
 */
@Service("professorService")
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private LaboratoryDao laboratoryDao;

    @Autowired
    private WeekCalculator weekCalculator;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LaboratoryDto getCurrentLaboratory(String professorPnc, LocalDateTime date)
        throws ServiceInvalidSemesterException, ServiceEntityNotFoundException {
        Hour from = new Hour(date.getHour() % 2 == 1 ? date.getHour() - 1 : date.getHour());
        Day day = new Day(date.getDayOfWeek().getValue());
        int week = weekCalculator.getWeek(date.toLocalDate());

        List<Laboratory> laboratories = laboratoryDao.getLaboratoriesByDateAndTime(professorPnc, from, day);

        if (laboratories.isEmpty()) {
            throw new ServiceEntityNotFoundException();
        }
        else if (laboratories.size() == 1) {
            return modelMapper.map(laboratories.get(0), LaboratoryDto.class);
        }

        LaboratoryDto first = modelMapper.map(laboratories.get(0), LaboratoryDto.class);
        LaboratoryDto second = modelMapper.map(laboratories.get(1), LaboratoryDto.class);

        if (week % 2 == 0) {
            if (first.getWeeklyOccurrence().equals("Even Week")) {
                return first;
            }
            else {
                return second;
            }
        }
        else {
            if (first.getWeeklyOccurrence().equals("Odd Week")) {
                return first;
            }
            else {
                return second;
            }
        }
    }
}
