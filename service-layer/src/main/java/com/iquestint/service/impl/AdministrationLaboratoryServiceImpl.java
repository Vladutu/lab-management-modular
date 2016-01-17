package com.iquestint.service.impl;

import com.iquestint.dao.*;
import com.iquestint.dto.LaboratoryDto;
import com.iquestint.dto.SectionDto;
import com.iquestint.dto.SemesterDto;
import com.iquestint.dto.YearDto;
import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.*;
import com.iquestint.service.AdministrationLaboratoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements the AdministrationLaboratoryService interface.
 *
 * @author Georgian Vladutu
 */
@Service("administrationLaboratoryService")
@Transactional
public class AdministrationLaboratoryServiceImpl implements AdministrationLaboratoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministrationLaboratoryServiceImpl.class);

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
    private GradeDao gradeDao;

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private NoteDao noteDao;

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
            LOGGER.debug("DaoEntityNotFoundException");
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoEntityAlreadyExistsException e) {
            LOGGER.debug("DaoEntityAlreadyExistsException");
            throw new ServiceEntityAlreadyExistsException(e);
        }
    }

    @Override
    public void deleteLaboratory(int id) throws ServiceEntityNotFoundException {
        try {
            gradeDao.deleteGradesByLaboratory(id);
            attendanceDao.deleteAttendancesByLaboratory(id);
            documentDao.deleteDocumentsByLaboratory(id);
            laboratoryDao.deleteLaboratoryById(id);
            noteDao.deleteNotesByLaboratory(id);
        } catch (DaoEntityNotFoundException e) {
            LOGGER.debug("DaoEntityNotFoundException");
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public LaboratoryDto getLaboratoryById(int id) throws ServiceEntityNotFoundException {
        try {
            Laboratory laboratory = laboratoryDao.getLaboratoryById(id);

            return modelMapper.map(laboratory, LaboratoryDto.class);
        } catch (DaoEntityNotFoundException e) {
            LOGGER.debug("DaoEntityNotFoundException");
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
    public void updateLaboratory(LaboratoryDto laboratoryDto) throws ServiceEntityNotFoundException {
        try {
            Laboratory laboratory = laboratoryDao.getLaboratoryById(laboratoryDto.getId());
            boolean studentsNeedUpdate = studentsNeedUpdate(laboratory, laboratoryDto);
            updateLaboratoryFields(laboratory, laboratoryDto);

            laboratoryDao.updateLaboratory(laboratory);

            if (studentsNeedUpdate) {
                laboratory.getStudents().clear();

                List<Student> students = studentDao.getStudents(laboratory.getSection(), laboratory.getYear(),
                    laboratory.getSemester(), laboratory.getGroup(), laboratory.getSubgroup());
                laboratory.setStudents(students);
            }

        } catch (DaoEntityNotFoundException e) {
            LOGGER.debug("DaoEntityNotFoundException");
            throw new ServiceEntityNotFoundException(e);
        }
    }

    private void updateLaboratoryFields(Laboratory laboratory, LaboratoryDto laboratoryDto)
        throws DaoEntityNotFoundException {

        String compressedFields = laboratoryDto.getFormProfessorDto().getCompressedFields();
        String pnc = compressedFields.replaceAll("\\)", " ").replaceAll("\\(", " ").split(" ")[0];

        Hour from = hourDao.getHourByValue(laboratoryDto.getFrom());
        Hour to = hourDao.getHourByValue(laboratoryDto.getTo());
        Professor professor = professorDao.getProfessorByPnc(pnc);
        Room room = roomDao.getRoomByName(laboratoryDto.getRoom());
        Day day = dayDao.getDayByValue(laboratoryDto.getDay());
        Section section = sectionDao.getSectionByName(laboratoryDto.getSection());
        Group group = groupDao.getGroupByName(laboratoryDto.getGroup());
        Subgroup subgroup = subgroupDao.getSubgroupByName(laboratoryDto.getSubgroup());
        Year year = yearDao.getYearByValue(laboratoryDto.getYear());
        Semester semester = semesterDao.getSemesterByValue(laboratoryDto.getSemester());
        WeeklyOccurrence weeklyOccurrence = weeklyOccurrenceDao.getWeeklyOccurrenceByName(
            laboratoryDto.getWeeklyOccurrence());

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
    }

    private boolean studentsNeedUpdate(Laboratory laboratory, LaboratoryDto laboratoryDto) {
        boolean groupMatches = laboratory.getGroup().getName().equals(laboratoryDto.getGroup());
        boolean subgroupMathces = laboratory.getSubgroup().getName().equals(laboratoryDto.getSubgroup());

        return !(subgroupMathces && groupMatches);
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
