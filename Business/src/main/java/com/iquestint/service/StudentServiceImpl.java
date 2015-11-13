package com.iquestint.service;

import com.iquestint.dao.GroupDao;
import com.iquestint.dao.SectionDao;
import com.iquestint.dao.StudentDao;
import com.iquestint.dao.SubgroupDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Group;
import com.iquestint.model.Section;
import com.iquestint.model.Student;
import com.iquestint.model.Subgroup;
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

    @Override
    public void saveStudent(Student student) throws ServiceEntityNotFoundException,
        ServiceEntityAlreadyExistsException {
        Section section = null;
        Group group = null;
        Subgroup subgroup = null;

        try {
            section = sectionDao.getSectionByName(student.getSection().getName());
            group = groupDao.getGroupByName(student.getGroup().getName());
            subgroup = subgroupDao.getSubgroupByName(student.getSubgroup().getName());
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        student.setGroup(group);
        student.setSubgroup(subgroup);
        student.setSection(section);

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

        try {
            section = sectionDao.getSectionByName(student.getSection().getName());
            group = groupDao.getGroupByName(student.getGroup().getName());
            subgroup = subgroupDao.getSubgroupByName(student.getSubgroup().getName());

            student.setGroup(group);
            student.setSubgroup(subgroup);
            student.setSection(section);

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
