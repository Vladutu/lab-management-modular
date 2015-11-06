package com.iquestint.service;

import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Student;

import java.util.List;

/**
 * @author vladu
 */
public interface StudentService {

    public void saveStudent(Student student) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

    public void deleteStudent(int id) throws ServiceEntityNotFoundException;

    public Student findStudentById(int id) throws ServiceEntityNotFoundException;

    public List<Student> findAllStudents();

    public void updateStudent(Student student) throws ServiceEntityNotFoundException;

    public Student getStudentByName(String firstName, String lastName) throws ServiceEntityNotFoundException;
}
