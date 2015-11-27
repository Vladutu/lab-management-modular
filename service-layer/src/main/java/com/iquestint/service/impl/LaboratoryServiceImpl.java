package com.iquestint.service.impl;

import com.iquestint.dao.*;
import com.iquestint.dto.LaboratoryDto;
import com.iquestint.dto.SectionDto;
import com.iquestint.dto.SemesterDto;
import com.iquestint.dto.YearDto;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.*;
import com.iquestint.service.LaboratoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
 */
@Service("laboratoryService")
@Transactional
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    private LaboratoryDao laboratoryDao;

    @Autowired
    private HourDao hourDao;

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private DayDao dayDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private SubgroupDao subgroupDao;

    @Autowired
    private YearDao yearDao;

    @Autowired
    private SemesterDao semesterDao;

    @Autowired
    private WeeklyOccurrenceDao weeklyOccurrenceDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveLaboratory(LaboratoryDto laboratoryDto)
        throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException {
        Laboratory laboratory = modelMapper.map(laboratoryDto, Laboratory.class);

        try {
            populateLaboratoryFields(laboratory);

            laboratoryDao.saveLaboratory(laboratory);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoEntityAlreadyExists daoEntityAlreadyExists) {
            daoEntityAlreadyExists.printStackTrace();
        }
    }

    @Override
    public void deleteLaboratory(int id) throws ServiceEntityNotFoundException {
        try {
            laboratoryDao.deleteLaboratoryById(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Laboratory getLaboratoryById(int id) throws ServiceEntityNotFoundException {
        try {
            return laboratoryDao.getLaboratoryById(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<Laboratory> getAllLaboratories() {
        return laboratoryDao.getAllLaboratories();
    }

    @Override
    public List<LaboratoryDto> getLaboratories(SectionDto sectionDto, YearDto yearDto, SemesterDto semesterDto) {
        Section section = modelMapper.map(sectionDto, Section.class);
        Year year = modelMapper.map(yearDto, Year.class);
        Semester semester = modelMapper.map(semesterDto, Semester.class);

        List<Laboratory> laboratories = laboratoryDao.getLaboratories(section, year, semester);

        return modelMapper.map(laboratories, new TypeToken<List<LaboratoryDto>>() {
        }.getType());
    }

    @Override
    public void updateLaboratory(Laboratory laboratory) throws ServiceEntityNotFoundException {
        Hour from = null;
        Hour to = null;
        Professor professor = null;
        Room room = null;
        Day day = null;
        Section section = null;
        Group group = null;
        Subgroup subgroup = null;
        Year year = null;
        Semester semester = null;
        WeeklyOccurrence weeklyOccurrence = null;

        try {
            from = hourDao.getHourByValue(laboratory.getFrom().getValue());
            to = hourDao.getHourByValue(laboratory.getTo().getValue());
            professor = professorDao.getProfessorByPnc(laboratory.getProfessor().getPnc());
            room = roomDao.getRoomByName(laboratory.getRoom().getName());
            day = dayDao.getDayByValue(laboratory.getDay().getValue());
            section = sectionDao.getSectionByName(laboratory.getSection().getName());
            group = groupDao.getGroupByName(laboratory.getGroup().getName());
            subgroup = subgroupDao.getSubgroupByName(laboratory.getSubgroup().getName());
            year = yearDao.getYearByValue(laboratory.getYear().getValue());
            semester = semesterDao.getSemesterByValue(laboratory.getSemester().getValue());
            weeklyOccurrence = weeklyOccurrenceDao.getWeeklyOccurrenceByName(
                laboratory.getWeeklyOccurrence().getName());

            laboratory.setFrom(from);
            laboratory.setTo(to);
            laboratory.setProfessor(professor);
            laboratory.setRoom(room);
            laboratory.setDay(day);
            laboratory.setSection(section);
            laboratory.setGroup(group);
            laboratory.setSubgroup(subgroup);
            laboratory.setYear(year);
            laboratory.setSemester(semester);
            laboratory.setWeeklyOccurrence(weeklyOccurrence);

            laboratoryDao.updateLaboratory(laboratory);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    private void populateLaboratoryFields(Laboratory laboratory)
        throws DaoEntityNotFoundException {
        Hour from = hourDao.getHourByValue(laboratory.getFrom().getValue());
        Hour to = hourDao.getHourByValue(laboratory.getTo().getValue());
        Professor professor = professorDao.getProfessorByPnc(laboratory.getProfessor().getPnc());
        Room room = roomDao.getRoomByName(laboratory.getRoom().getName());
        Day day = dayDao.getDayByValue(laboratory.getDay().getValue());
        Section section = sectionDao.getSectionByName(laboratory.getSection().getName());
        Group group = groupDao.getGroupByName(laboratory.getGroup().getName());
        Subgroup subgroup = subgroupDao.getSubgroupByName(laboratory.getSubgroup().getName());
        Year year = yearDao.getYearByValue(laboratory.getYear().getValue());
        Semester semester = semesterDao.getSemesterByValue(laboratory.getSemester().getValue());
        WeeklyOccurrence weeklyOccurrence = weeklyOccurrenceDao.getWeeklyOccurrenceByName(
            laboratory.getWeeklyOccurrence().getName());
        List<Student> students = studentDao.getStudents(section, year, semester, group, subgroup);

        laboratory.setFrom(from);
        laboratory.setTo(to);
        laboratory.setProfessor(professor);
        laboratory.setRoom(room);
        laboratory.setDay(day);
        laboratory.setSection(section);
        laboratory.setGroup(group);
        laboratory.setSubgroup(subgroup);
        laboratory.setYear(year);
        laboratory.setSemester(semester);
        laboratory.setWeeklyOccurrence(weeklyOccurrence);
        laboratory.setStudents(students);
    }
}
