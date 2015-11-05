package com.iquestint.service;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Student;

import java.util.List;

/**
 * @author vladu
 */
public interface StudentService {
    public void saveStudent(Student student) throws DaoEntityAlreadyExists, DaoEntityNotFoundException;

    public void deleteStudent(int id) throws DaoEntityNotFoundException;

    public Student findStudentById(int id) throws DaoEntityNotFoundException;

    public List<Student> findAllStudents();

    public void updateStudent(Student student) throws DaoEntityNotFoundException;

    public Student getStudentByName(String firstName, String lastName) throws DaoEntityNotFoundException;
}
