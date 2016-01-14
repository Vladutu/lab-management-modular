package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.*;

import java.util.List;

/**
 * This interfaces provides methods for working with Student entity explicitly (and Student database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface StudentDao {

    /**
     * Returns all Student entities from the database.
     *
     * @return List<Student>
     */
    List<Student> getAllStudents();

    /**
     * Returns the Student entity from the database which has the same personal numeric code(pnc) as the method
     * parameter.
     *
     * @param pnc the personal numeric code of the student
     * @return Student
     * @throws DaoEntityNotFoundException if the student with the specified pnc is not found
     */
    Student getStudentByPnc(String pnc) throws DaoEntityNotFoundException;

    /**
     * Returns the Student entity from the database which has the same first name and last name as the method parameters.
     *
     * @param firstName student's first name
     * @param lastName  student's last name
     * @return Student
     * @throws DaoEntityNotFoundException if the student with the specified name is not found
     */
    Student getStudentByName(String firstName, String lastName) throws DaoEntityNotFoundException;

    /**
     * Saves student into the database.
     *
     * @param student the student to be saved
     * @throws DaoEntityAlreadyExistsException if the student already exists in the database
     */
    void saveStudent(Student student) throws DaoEntityAlreadyExistsException;

    /**
     * Updates student into the database. This student primary key must already be present in the database.
     *
     * @param student student to be updated
     * @throws DaoEntityNotFoundException if the student is not found
     */
    void updateStudent(Student student) throws DaoEntityNotFoundException;

    /**
     * Deletes the student from the database by specifying his/her personal numeric code.
     *
     * @param pnc the personal numeric code of the student
     * @throws DaoEntityNotFoundException if the student with the specified pnc is not found
     */
    void deleteStudentByPnc(String pnc) throws DaoEntityNotFoundException;

    /**
     * Returns the students that have the same section, year, semester, group, subgroup as the method parameters.
     *
     * @param section  Section
     * @param year     Year
     * @param semester Semester
     * @param group    Group
     * @param subgroup Subgroup
     * @return List<Student>
     */
    List<Student> getStudents(Section section, Year year, Semester semester, Group group, Subgroup subgroup);

    /**
     * Returns all students which belong to the laboratory specified as the method parameter.
     *
     * @param laboratory laboratory which the students belong to
     * @return List<Student>
     */
    List<Student> getStudents(Laboratory laboratory);
}
