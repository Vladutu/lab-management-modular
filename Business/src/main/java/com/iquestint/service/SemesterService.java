package com.iquestint.service;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Semester;

import java.util.List;

/**
 * @author vladu
 */
public interface SemesterService {

    List<Semester> getAllSemesters();

    Semester getSemesterByValue(int value) throws DaoEntityNotFoundException;
}
