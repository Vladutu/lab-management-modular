package com.iquestint.service;

import com.iquestint.dao.GroupDao;
import com.iquestint.dao.SectionDao;
import com.iquestint.dao.StudentDao;
import com.iquestint.dao.SubgroupDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Group;
import com.iquestint.model.Section;
import com.iquestint.model.Student;
import com.iquestint.model.Subgroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
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

    public void saveStudent(Student student) throws DaoEntityAlreadyExists {
        Section section = sectionDao.getSectionByName(student.getSection().getName());
        Group group = groupDao.getGroupByName(student.getGroup().getName());
        Subgroup subgroup = subgroupDao.getSubgroupByName(student.getSubgroup().getName());

        student.setGroup(group);
        student.setSubgroup(subgroup);
        student.setSection(section);

        studentDao.saveStudent(student);
    }

    public void deleteStudent(int id) throws DaoEntityNotFoundException {
        studentDao.deleteStudentById(id);
    }

    public Student findStudentById(int id) throws DaoEntityNotFoundException {
        return studentDao.findById(id);
    }

    public List<Student> findAllStudents() {
        return studentDao.findAllStudents();
    }

    public void updateStudent(Student student) throws DaoEntityNotFoundException {
        studentDao.updateStudent(student);
    }

    public Student getStudentByName(String firstName, String lastName) throws DaoEntityNotFoundException {
        return studentDao.findStudentByName(firstName, lastName);
    }
}
