package com.iquestint.service;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Student;

import java.util.List;

/**
 * @author vladu
 */
public interface StudentService {
    public void saveStudent(Student student);

    public void deleteStudent(int id);

    public Student findStudentById(int id) throws DaoEntityNotFoundException;

    public List<Student> findAllStudents();

    public void updateStudent(Student student);
}
