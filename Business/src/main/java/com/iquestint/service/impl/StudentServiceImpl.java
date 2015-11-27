package com.iquestint.service.impl;

import com.iquestint.dao.*;
import com.iquestint.dto.StudentDto;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.*;
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
    private LaboratoryDao laboratoryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private SubgroupDao subgroupDao;

    @Autowired
    private SemesterDao semesterDao;

    @Autowired
    private YearDao yearDao;

    @Override
    public void saveStudent(StudentDto studentDto) throws ServiceEntityNotFoundException,
        ServiceEntityAlreadyExistsException {
        Student student = modelMapper.map(studentDto, Student.class);

        try {
            populateStudentSpecificFields(student, studentDto);
            List<Laboratory> laboratories = laboratoryDao.getLaboratories(student.getSection(), student.getYear(),
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
            Student student = studentDao.getStudentByPnc(pnc);
            List<Laboratory> laboratories = student.getLaboratories();
            removeStudentFromLaboratories(student, laboratories);

            try {
                userDao.deleteUserByPnc(pnc);
            } catch (DaoEntityNotFoundException ignored) {

            }

            studentDao.deleteStudentByPnc(pnc);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public StudentDto getStudentByPnc(String pnc) throws ServiceEntityNotFoundException {
        try {
            Student student = studentDao.getStudentByPnc(pnc);

            return modelMapper.map(student, StudentDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentDao.getAllStudents();
        Type listType = new TypeToken<List<StudentDto>>() {
        }.getType();

        return modelMapper.map(students, listType);
    }

    @Override
    public void updateStudent(StudentDto studentDto) throws ServiceEntityNotFoundException {

        try {
            Student student = studentDao.getStudentByPnc(studentDto.getPnc());

            boolean laboratoryNeedsUpdate = laboratoryNeedsUpdate(studentDto, student);
            populateStudentSpecificFields(student, studentDto);
            populatePersonSpecificFields(student, studentDto);

            studentDao.updateStudent(student);

            if (laboratoryNeedsUpdate) {
                List<Laboratory> laboratories = student.getLaboratories();
                removeStudentFromLaboratories(student, laboratories);

                laboratories = laboratoryDao.getLaboratories(student.getSection(), student.getYear(),
                    student.getSemester(), student.getGroup(), student.getSubgroup());
                addStudentToLaboratories(student, laboratories);
            }

        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Student getStudentByName(String firstName, String lastName) throws ServiceEntityNotFoundException {
        try {
            return studentDao.getStudentByName(firstName, lastName);
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

    private boolean laboratoryNeedsUpdate(StudentDto studentDto, Student student) {
        boolean sectionMatches = studentDto.getSection().equals(student.getSection().getName());
        boolean groupMatches = studentDto.getGroup().equals(student.getGroup().getName());
        boolean subgroupMatches = studentDto.getSubgroup().equals(student.getSubgroup().getName());
        boolean yearMatches = studentDto.getYear().equals(student.getYear().getValue());
        boolean semesterMatches = studentDto.getSemester().equals(student.getSemester().getValue());

        return !(sectionMatches && groupMatches && subgroupMatches && yearMatches && semesterMatches);
    }

    private void populateStudentSpecificFields(Student student, StudentDto studentDto)
        throws DaoEntityNotFoundException {
        Section section = sectionDao.getSectionByName(studentDto.getSection());
        Group group = groupDao.getGroupByName(studentDto.getGroup());
        Subgroup subgroup = subgroupDao.getSubgroupByName(studentDto.getSubgroup());
        Year year = yearDao.getYearByValue(studentDto.getYear());
        Semester semester = semesterDao.getSemesterByValue(studentDto.getSemester());

        student.setGroup(group);
        student.setSubgroup(subgroup);
        student.setSection(section);
        student.setYear(year);
        student.setSemester(semester);
    }

    private void populatePersonSpecificFields(Student student, StudentDto studentDto) {
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setPnc(studentDto.getPnc());
    }
}
