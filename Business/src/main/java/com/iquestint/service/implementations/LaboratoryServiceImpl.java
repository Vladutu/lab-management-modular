package com.iquestint.service.implementations;

import com.iquestint.dao.interfaces.*;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.*;
import com.iquestint.service.interfaces.LaboratoryService;
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

    @Override
    public void saveLaboratory(Laboratory laboratory)
        throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException {
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
        List<Student> students = null;

        try {
            from = hourDao.getHourByValue(laboratory.getFrom().getValue());
            to = hourDao.getHourByValue(laboratory.getTo().getValue());
            professor = professorDao.findProfessorByPnc(laboratory.getProfessor().getPnc());
            room = roomDao.getRoomByName(laboratory.getRoom().getName());
            day = dayDao.getDayByValue(laboratory.getDay().getValue());
            section = sectionDao.getSectionByName(laboratory.getSection().getName());
            group = groupDao.getGroupByName(laboratory.getGroup().getName());
            subgroup = subgroupDao.getSubgroupByName(laboratory.getSubgroup().getName());
            year = yearDao.getYearByValue(laboratory.getYear().getValue());
            semester = semesterDao.getSemesterByValue(laboratory.getSemester().getValue());
            weeklyOccurrence = weeklyOccurrenceDao.getWeeklyOccurrenceByName(
                laboratory.getWeeklyOccurrence().getName());
            students = studentDao.findStudents(section, year, semester, group, subgroup);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

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

        try {
            laboratoryDao.saveLaboratory(laboratory);
        }
        catch (DaoEntityAlreadyExists e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }

    }

    @Override
    public void deleteLaboratory(int id) throws ServiceEntityNotFoundException {
        try {
            laboratoryDao.deleteLaboratoryById(id);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Laboratory getLaboratoryById(int id) throws ServiceEntityNotFoundException {
        try {
            return laboratoryDao.findLaboratoryById(id);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<Laboratory> getAllLaboratories() {
        return laboratoryDao.findAllLaboratories();
    }

    @Override
    public List<Laboratory> getLaboratories(Section section, Year year, Semester semester) {
        return laboratoryDao.findLaboratories(section, year, semester);
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
            professor = professorDao.findProfessorByPnc(laboratory.getProfessor().getPnc());
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
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
