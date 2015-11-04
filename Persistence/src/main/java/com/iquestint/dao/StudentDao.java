package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Student;

import java.util.List;

/**
 * @author vladu
 */
public interface StudentDao {
    List<Student> findAllStudents();

    Student findById(int id) throws DaoEntityNotFoundException;

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(int id);
}
