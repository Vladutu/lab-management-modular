package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Semester;

import java.util.List;

/**
 * This interfaces provides methods for working with Semester entity explicitly (and Semester database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface SemesterDao {

    /**
     * Returns the Semester entity from the database which has the same value as the method parameter.
     *
     * @param value value of the semester
     * @return Semester
     * @throws DaoEntityNotFoundException if the semester with the specified value is not found
     */
    Semester getSemesterByValue(int value) throws DaoEntityNotFoundException;

    /**
     * Returns all Semester entities from the database.
     *
     * @return list of semesters
     */
    List<Semester> getAllSemesters();
}
