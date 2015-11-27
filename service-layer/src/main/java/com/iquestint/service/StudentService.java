package com.iquestint.service;

import com.iquestint.dto.StudentDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Student;

import java.util.List;

/**
 * This interfaces provides methods for manipulating Student objects.
 *
 * @author Georgian Vladutu
 */
public interface StudentService {

    /**
     * This method saves a student. It throws ServiceEntityNotFoundException if any of it's component fields are
     * not found and ServiceEntityAlreadyExistsException if the student was already saved.
     *
     * @param studentDto student to be saved
     * @throws ServiceEntityNotFoundException
     * @throws ServiceEntityAlreadyExistsException
     */
    void saveStudent(StudentDto studentDto) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

    /**
     * This method deletes a student by specifying his/her personal numeric code(pnc). It throws ServiceEntityNotFoundException
     * if the student is not found.
     *
     * @param pnc the personal numeric code of the student
     * @throws ServiceEntityNotFoundException
     */
    void deleteStudent(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a student by specifying it's personal numeric code(pnc). It throws ServiceEntityNotFoundException
     * if the student is not found.
     *
     * @param pnc the personal numeric code of the student
     * @return Student
     * @throws ServiceEntityNotFoundException
     */
    StudentDto getStudentByPnc(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves all students and maps them to StudentDto model.
     *
     * @return List<StudentDto>
     */
    List<StudentDto> getAllStudents();

    /**
     * This method update a student. It throws ServiceEntityNotFoundException if the student is not found.
     *
     * @param studentDto the student to be updated
     * @throws ServiceEntityNotFoundException
     */
    void updateStudent(StudentDto studentDto) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a student by specifying his/her first name and last name. It throws
     * ServiceEntityNotFoundException if the student is not found.
     *
     * @param firstName the student's first name
     * @param lastName  the student's last name
     * @return Student
     * @throws ServiceEntityNotFoundException
     */
    Student getStudentByName(String firstName, String lastName) throws ServiceEntityNotFoundException;
}
