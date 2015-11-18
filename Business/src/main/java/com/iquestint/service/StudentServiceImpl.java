package com.iquestint.service;

import com.iquestint.dao.*;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class implements the StudentService interface.
 *
 * @author Georgian Vladutu
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

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

    @Override
    public void saveStudent(Student student) throws ServiceEntityNotFoundException,
        ServiceEntityAlreadyExistsException {
        Section section = null;
        Group group = null;
        Subgroup subgroup = null;
        Year year = null;
        Semester semester = null;

        try {
            section = sectionDao.getSectionByName(student.getSection().getName());
            group = groupDao.getGroupByName(student.getGroup().getName());
            subgroup = subgroupDao.getSubgroupByName(student.getSubgroup().getName());
            year = yearDao.getYearByName(student.getYear().getName());
            semester = semesterDao.getSemesterByName(student.getSemester().getName());
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        student.setGroup(group);
        student.setSubgroup(subgroup);
        student.setSection(section);
        student.setYear(year);
        student.setSemester(semester);

        try {
            studentDao.saveStudent(student);
        }
        catch (DaoEntityAlreadyExists e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }
    }

    @Override
    public void deleteStudent(String pnc) throws ServiceEntityNotFoundException {
        try {
            studentDao.deleteStudentByPnc(pnc);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Student getStudentByPnc(String pnc) throws ServiceEntityNotFoundException {
        try {
            return studentDao.findByPnc(pnc);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAllStudents();
    }

    @Override
    public void updateStudent(Student student) throws ServiceEntityNotFoundException {
        Section section = null;
        Group group = null;
        Subgroup subgroup = null;
        Year year = null;
        Semester semester = null;

        try {
            section = sectionDao.getSectionByName(student.getSection().getName());
            group = groupDao.getGroupByName(student.getGroup().getName());
            subgroup = subgroupDao.getSubgroupByName(student.getSubgroup().getName());
            year = yearDao.getYearByName(student.getYear().getName());
            semester = semesterDao.getSemesterByName(student.getSemester().getName());

            student.setGroup(group);
            student.setSubgroup(subgroup);
            student.setSection(section);
            student.setYear(year);
            student.setSemester(semester);

            studentDao.updateStudent(student);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Student getStudentByName(String firstName, String lastName) throws ServiceEntityNotFoundException {
        try {
            return studentDao.findStudentByName(firstName, lastName);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
