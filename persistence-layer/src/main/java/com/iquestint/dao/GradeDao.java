package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Grade;

import java.util.List;

/**
 * @author vladu
 */
public interface GradeDao {

    Grade getGradeById(int id) throws DaoEntityNotFoundException;

    void saveGrade(Grade grade) throws DaoEntityAlreadyExists;

    List<Grade> getStudentGrades(String studentPnc);
}
