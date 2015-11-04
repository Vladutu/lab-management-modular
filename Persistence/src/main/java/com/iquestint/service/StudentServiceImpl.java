package com.iquestint.service;

import com.iquestint.dao.StudentDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Student;
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

    public void saveStudent(Student student) {
        studentDao.saveStudent(student);
    }

    public void deleteStudent(int id) {
        studentDao.deleteStudentById(id);
    }

    public Student findStudentById(int id) throws DaoEntityNotFoundException {
        return studentDao.findById(id);
    }

    public List<Student> findAllStudents() {
        return studentDao.findAllStudents();
    }

    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }
}
