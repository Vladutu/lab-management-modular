package com.iquestint.service;

import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Student;

import java.util.List;

/**
 * This interface provides methods for manipulating Student objects.
 *
 * @author Georgian Vladutu
 */
public interface StudentService {

    /**
     * This method saves a student. It throws ServiceEntityNotFoundException if any of it's component fields are
     * not found
     * and ServiceEntityAlreadyExistsException if the student was already saved.
     *
     * @param student student to be saved
     * @throws ServiceEntityNotFoundException
     * @throws ServiceEntityAlreadyExistsException
     */
    public void saveStudent(Student student) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

    /**
     * This method deletes a student by specifying his/her personal numeric code(pnc). It throws ServiceEntityNotFoundException
     * if the student is not found.
     *
     * @param pnc the personal numeric code of the student
     * @throws ServiceEntityNotFoundException
     */
    public void deleteStudent(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a student by specifying it's personal numeric code(pnc). It throws ServiceEntityNotFoundException
     * if the student is not found.
     *
     * @param pnc the personal numeric code of the student
     * @return Student
     * @throws ServiceEntityNotFoundException
     */
    public Student getStudentById(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves all students.
     *
     * @return List<Student>
     */
    public List<Student> getAllStudents();

    /**
     * This method update a student. It throws ServiceEntityNotFoundException if the student is not found.
     *
     * @param student the student to be updated
     * @throws ServiceEntityNotFoundException
     */
    public void updateStudent(Student student) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a student by specifying his/her first name and last name. It throws
     * ServiceEntityNotFoundException if the student is not found.
     *
     * @param firstName the student's first name
     * @param lastName  the student's last name
     * @return Student
     * @throws ServiceEntityNotFoundException
     */
    public Student getStudentByName(String firstName, String lastName) throws ServiceEntityNotFoundException;
}
