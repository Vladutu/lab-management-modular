package com.iquestint.dao;

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
     * Saves the student into the database.
     *
     * @param student the student to be saved
     */
    void saveStudent(Student student);

    /**
     * Updates the student into the database. This student primary key must already be present in the database.
     *
     * @param student student to be updated
     */
    void updateStudent(Student student);

    /**
     * Deletes the student from the database by specifying his/her id.
     *
     * @param id the id of the student
     */
    void deleteStudentById(int id);
}
