package com.iquestint.service.impl;

import com.iquestint.dao.*;
import com.iquestint.dto.*;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.*;
import com.iquestint.populator.StudentPopulator;
import com.iquestint.service.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

/**
 * This class implements the StudentService interfaces.
 *
 * @author Georgian Vladutu
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private UserDao userDao;

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
    private LaboratoryDao laboratoryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentPopulator studentPopulator;

    @Override
    public void saveStudent(StudentDto studentDto) throws ServiceEntityNotFoundException,
        ServiceEntityAlreadyExistsException {
        Student student = modelMapper.map(studentDto, Student.class);

        try {
            studentPopulator.populateStudentSpecificFields(student, studentDto);
            List<Laboratory> laboratories = laboratoryDao.findLaboratories(student.getSection(), student.getYear(),
                student.getSemester(), student.getGroup(), student.getSubgroup());

            studentDao.saveStudent(student);

            addStudentToLaboratories(student, laboratories);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoEntityAlreadyExists e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }

    }

    @Override
    public void deleteStudent(String pnc) throws ServiceEntityNotFoundException {
        try {
            Student student = studentDao.findStudentByPnc(pnc);
            List<Laboratory> laboratories = student.getLaboratories();
            removeStudentFromLaboratories(student, laboratories);

            userDao.deleteUserByPnc(pnc);

            studentDao.deleteStudentByPnc(pnc);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public StudentDto getStudentByPnc(String pnc) throws ServiceEntityNotFoundException {
        try {
            Student student = studentDao.findStudentByPnc(pnc);

            return modelMapper.map(student, StudentDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentDao.findAllStudents();
        Type listType = new TypeToken<List<StudentDto>>() {
        }.getType();

        return modelMapper.map(students, listType);
    }

    @Override
    public void initializeFormStudentDto(FormStudentDto formStudentDto) {
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
    }

    @Override
    public void updateStudent(StudentDto studentDto) throws ServiceEntityNotFoundException {

        try {
            Student student = studentDao.findStudentByPnc(studentDto.getPnc());

            studentPopulator.populateStudentSpecificFields(student, studentDto);
            studentPopulator.populatePersonSpecificFields(student, studentDto);

            studentDao.updateStudent(student);

            List<Laboratory> laboratories = student.getLaboratories();
            removeStudentFromLaboratories(student, laboratories);

            laboratories = laboratoryDao.findLaboratories(student.getSection(), student.getYear(),
                student.getSemester(), student.getGroup(), student.getSubgroup());
            addStudentToLaboratories(student, laboratories);

        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Student getStudentByName(String firstName, String lastName) throws ServiceEntityNotFoundException {
        try {
            return studentDao.findStudentByName(firstName, lastName);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    private void addStudentToLaboratories(Student student, List<Laboratory> laboratories)
        throws DaoEntityNotFoundException {
        for (Laboratory laboratory : laboratories) {
            laboratory.getStudents().add(student);
            laboratoryDao.updateLaboratory(laboratory);
        }
    }

    private void removeStudentFromLaboratories(Student student, List<Laboratory> laboratories)
        throws DaoEntityNotFoundException {
        for (Laboratory laboratory : laboratories) {
            laboratory.getStudents().remove(student);
            laboratoryDao.updateLaboratory(laboratory);
        }
    }
}
