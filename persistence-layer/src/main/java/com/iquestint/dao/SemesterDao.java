package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Semester;

import java.util.List;

/**
 * @author vladu
 */
public interface SemesterDao {

    Semester getSemesterByValue(int value) throws DaoEntityNotFoundException;

    List<Semester> getAllSemesters();
}
