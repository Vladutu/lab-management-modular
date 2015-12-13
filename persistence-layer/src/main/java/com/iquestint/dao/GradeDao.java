package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Grade;

import java.util.List;

/**
 * This interfaces provides methods for working with Grade entity explicitly (and Grade database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface GradeDao {

    /**
     * Returns the Grade entity from the database which has the same id as the method parameter.
     *
     * @param id id of the grade
     * @return Grade
     * @throws DaoEntityNotFoundException if the grade is not found in the database
     */
    Grade getGradeById(int id) throws DaoEntityNotFoundException;

    /**
     * Saves the grade entity into the database.
     *
     * @param grade grade to be saved
     * @throws DaoEntityAlreadyExists if the grade already exists in the database
     */
    void saveGrade(Grade grade) throws DaoEntityAlreadyExists;

    /**
     * Updates the grade into the database.
     *
     * @param grade grade to be updated
     * @throws DaoEntityNotFoundException if the grade is not found in the database
     */
    void updateGrade(Grade grade) throws DaoEntityNotFoundException;

    /**
     * Deletes all grades from the database by specifying their student pnc.
     *
     * @param studentPnc pnc of the student who has the grades
     */
    void deleteGradesByStudent(String studentPnc);

    /**
     * Deletes all grades from the database by specifying their laboratory id.
     *
     * @param laboratoryId id of the laboratory which the grades belongs to
     */
    void deleteGradesByLaboratory(int laboratoryId);

    /**
     * Returns all grades of the student whose pnc is the same as the method parameter.
     *
     * @param studentPnc pnc of the student
     * @return List<Grade>
     */
    List<Grade> getStudentGrades(String studentPnc);
}
