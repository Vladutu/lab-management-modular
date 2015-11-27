package com.iquestint.service.impl;

import com.iquestint.dao.*;
import com.iquestint.dto.*;
import com.iquestint.model.*;
import com.iquestint.service.AdministrationFormService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
 */
@Service("administrationFormService")
@Transactional
public class AdministrationFormServiceImpl implements AdministrationFormService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private SubgroupDao subgroupDao;

    @Autowired
    private YearDao yearDao;

    @Autowired
    private SemesterDao semesterDao;

    @Autowired
    private UserStateDao userStateDao;

    @Autowired
    private UserTypeDao userTypeDao;

    @Autowired
    private HourDao hourDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private WeeklyOccurrenceDao weeklyOccurrenceDao;

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private DayDao dayDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public FormStudentDto getFormStudent() {
        FormStudentDto formStudentDto = new FormStudentDto();

        List<Section> sections = sectionDao.getAllSections();
        List<Group> groups = groupDao.getAllGroups();
        List<Subgroup> subgroups = subgroupDao.getAllSubgroups();
        List<Semester> semesters = semesterDao.getAllSemesters();
        List<Year> years = yearDao.getAllYears();

        List<SectionDto> sectionDtos = modelMapper.map(sections, new TypeToken<List<SectionDto>>() {
        }.getType());
        List<GroupDto> groupDtos = modelMapper.map(groups, new TypeToken<List<GroupDto>>() {
        }.getType());
        List<SubgroupDto> subgroupDtos = modelMapper.map(subgroups, new TypeToken<List<SubgroupDto>>() {
        }.getType());
        List<SemesterDto> semesterDtos = modelMapper.map(semesters, new TypeToken<List<SemesterDto>>() {
        }.getType());
        List<YearDto> yearDtos = modelMapper.map(years, new TypeToken<List<YearDto>>() {
        }.getType());

        formStudentDto.setSections(sectionDtos);
        formStudentDto.setGroups(groupDtos);
        formStudentDto.setSubgroups(subgroupDtos);
        formStudentDto.setSemesters(semesterDtos);
        formStudentDto.setYears(yearDtos);

        return formStudentDto;
    }

    @Override
    public FormUserDto getFormUser() {
        FormUserDto formUserDto = new FormUserDto();

        List<UserType> userTypes = userTypeDao.getAllUserTypes();
        List<UserState> userStates = userStateDao.getAllUserStates();

        List<UserTypeDto> userTypeDtos = modelMapper.map(userTypes, new TypeToken<List<UserTypeDto>>() {
        }.getType());
        List<UserStateDto> userStateDtos = modelMapper.map(userStates, new TypeToken<List<UserStateDto>>() {
        }.getType());

        formUserDto.setStates(userStateDtos);
        formUserDto.setTypes(userTypeDtos);

        return formUserDto;
    }

    @Override
    public FormLaboratoryShowDto getFormLaboratoryShowDto() {
        FormLaboratoryShowDto formLaboratoryShowDto = new FormLaboratoryShowDto();

        List<Section> sections = sectionDao.getAllSections();
        List<Year> years = yearDao.getAllYears();
        List<Semester> semesters = semesterDao.getAllSemesters();

        List<SectionDto> sectionDtos = modelMapper.map(sections, new TypeToken<List<SectionDto>>() {
        }.getType());
        List<SemesterDto> semesterDtos = modelMapper.map(semesters, new TypeToken<List<SemesterDto>>() {
        }.getType());
        List<YearDto> yearDtos = modelMapper.map(years, new TypeToken<List<YearDto>>() {
        }.getType());

        formLaboratoryShowDto.setSections(sectionDtos);
        formLaboratoryShowDto.setSemesters(semesterDtos);
        formLaboratoryShowDto.setYears(yearDtos);

        return formLaboratoryShowDto;
    }

    @Override
    public FormLaboratoryCreateDto getFormLaboratoryCreateDto() {
        FormLaboratoryCreateDto formLaboratoryCreateDto = new FormLaboratoryCreateDto();

        List<Hour> hours = hourDao.getAllHours();
        List<Day> days = dayDao.getAllDays();
        List<Room> rooms = roomDao.getAllRooms();
        List<Group> groups = groupDao.getAllGroups();
        List<Subgroup> subgroups = subgroupDao.getAllSubgroups();
        List<WeeklyOccurrence> weeklyOccurrences = weeklyOccurrenceDao.getAllWeeklyOccurrences();
        List<Professor> professors = professorDao.getAllProfessors();

        List<HourDto> hourDtos = modelMapper.map(hours, new TypeToken<List<HourDto>>() {
        }.getType());
        List<DayDto> dayDtos = modelMapper.map(days, new TypeToken<List<DayDto>>() {
        }.getType());
        List<RoomDto> roomDtos = modelMapper.map(rooms, new TypeToken<List<RoomDto>>() {
        }.getType());
        List<GroupDto> groupDtos = modelMapper.map(groups, new TypeToken<List<GroupDto>>() {
        }.getType());
        List<SubgroupDto> subgroupDtos = modelMapper.map(subgroups, new TypeToken<List<SubgroupDto>>() {
        }.getType());
        List<WeeklyOccurrenceDto> weeklyOccurrenceDtos = modelMapper.map(weeklyOccurrences,
            new TypeToken<List<WeeklyOccurrenceDto>>() {
            }.getType());
        List<FormProfessorDto> formProfessorDtos = modelMapper.map(professors, new TypeToken<List<FormProfessorDto>>() {
        }.getType());

        formLaboratoryCreateDto.setDays(dayDtos);
        formLaboratoryCreateDto.setGroups(groupDtos);
        formLaboratoryCreateDto.setHours(hourDtos);
        formLaboratoryCreateDto.setProfessors(formProfessorDtos);
        formLaboratoryCreateDto.setRooms(roomDtos);
        formLaboratoryCreateDto.setSubgroups(subgroupDtos);
        formLaboratoryCreateDto.setWeeklyOccurrences(weeklyOccurrenceDtos);

        return formLaboratoryCreateDto;
    }
}
