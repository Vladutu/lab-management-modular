package com.iquestint.service.impl;

import com.iquestint.dao.LaboratoryDao;
import com.iquestint.dao.StudentDao;
import com.iquestint.dto.ProfessorLaboratoryDto;
import com.iquestint.dto.StudentDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceInvalidSemesterException;
import com.iquestint.model.Day;
import com.iquestint.model.Hour;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Student;
import com.iquestint.service.ProfessorService;
import com.iquestint.utils.WeekCalculator;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    private StudentDao studentDao;

    @Autowired
    private WeekCalculator weekCalculator;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProfessorLaboratoryDto getCurrentLaboratory(String professorPnc, LocalDateTime date)
        throws ServiceInvalidSemesterException, ServiceEntityNotFoundException {
        Laboratory laboratory = getLaboratory(professorPnc, date);
        List<Student> students = studentDao.getStudents(laboratory);

        ProfessorLaboratoryDto professorLaboratoryDto = new ProfessorLaboratoryDto();
        professorLaboratoryDto.setName(laboratory.getName());
        professorLaboratoryDto.setStudents(modelMapper.map(students, new TypeToken<List<StudentDto>>() {
        }.getType()));

        return professorLaboratoryDto;
    }

    private Laboratory getLaboratory(String professorPnc, LocalDateTime date)
        throws ServiceInvalidSemesterException, ServiceEntityNotFoundException {
        Hour from = new Hour(date.getHour() % 2 == 1 ? date.getHour() - 1 : date.getHour());
        Day day = new Day(date.getDayOfWeek().getValue());
        int week = weekCalculator.getWeek(date.toLocalDate());

        List<Laboratory> laboratories = laboratoryDao.getLaboratoriesByDateAndTime(professorPnc, from, day);

        if (laboratories.isEmpty()) {
            throw new ServiceEntityNotFoundException();
        }
        else if (laboratories.size() == 1) {
            return laboratories.get(0);
        }

        Laboratory first = laboratories.get(0);
        Laboratory second = laboratories.get(1);

        if (week % 2 == 0) {
            if (first.getWeeklyOccurrence().getName().equals("Even Week")) {
                return first;
            }
            else {
                return second;
            }
        }
        else {
            if (first.getWeeklyOccurrence().getName().equals("Odd Week")) {
                return first;
            }
            else {
                return second;
            }
        }
    }
}
