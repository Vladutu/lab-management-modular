package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Student;

import java.util.List;

/**
 * This interface provides methods for working with Student entity explicitly (and Student database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface StudentDao {

    /**
     * Returns all Student entities from the database.
     *
     * @return List<Student>
     */
    List<Student> findAllStudents();

    /**
     * Returns the Student entity from the database which has the same id as the method parameter. This method throws
     * DaoEntityNotFoundException if the student with the specified id is not found.
     *
     * @param id the id of the student
     * @return Student
     * @throws DaoEntityNotFoundException
     */
    Student findById(int id) throws DaoEntityNotFoundException;

    /**
     * Returns the Student entity from the database which has the same first name and last name as the method parameters.
     * This method throws DaoEntityNotFoundException if the student with the specified name is not found.
     *
     * @param firstName student's first name
     * @param lastName  student's last name
     * @return Student
     * @throws DaoEntityNotFoundException
     */
    Student findStudentByName(String firstName, String lastName) throws DaoEntityNotFoundException;

    /**
     * Saves the student into the database. This method throws DaoEntityAlreadyExists if the student already exists
     * in the database.
     *
     * @param student the student to be saved
     * @throws DaoEntityAlreadyExists
     */
    void saveStudent(Student student) throws DaoEntityAlreadyExists;

    /**
     * Updates the student into the database. This student primary key must already be present in the database.
     *
     * @param student student to be updated
     */
    void updateStudent(Student student) throws DaoEntityNotFoundException;

    /**
     * Deletes the student from the database by specifying his/her id.
     *
     * @param id the id of the student
     */
    void deleteStudentById(int id) throws DaoEntityNotFoundException;
}
