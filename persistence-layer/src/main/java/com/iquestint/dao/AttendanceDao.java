package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Attendance;

import java.util.List;

/**
 * This interfaces provides methods for working with Attendance entity explicitly (and Attendance database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface AttendanceDao {

    /**
     * Returns the Attendance entity from the database which has the same id as the method parameter.
     *
     * @param id the id of the entity
     * @return Attendance
     * @throws DaoEntityNotFoundException if the attendance with the specified id is not found
     */
    Attendance getAttendanceById(int id) throws DaoEntityNotFoundException;

    /**
     * Saves the student into the database.
     *
     * @param attendance attendance to be saved
     * @throws DaoEntityAlreadyExistsException if the attendance already exists in the database
     */
    void saveAttendance(Attendance attendance) throws DaoEntityAlreadyExistsException;

    /**
     * Updates the attendance into the database.
     *
     * @param attendance attendance to be updated
     * @throws DaoEntityNotFoundException if the attendance is not found in the database
     */
    void updateAttendance(Attendance attendance) throws DaoEntityNotFoundException;

    /**
     * Deletes all attendances from the database by specifying their student pnc.
     *
     * @param studentPnc pnc of the student who has the attendance
     */
    void deleteAttendancesByStudent(String studentPnc);

    /**
     * Deletes all attendances from the database by specifying their laboratory id.
     *
     * @param laboratoryId id of the laboratory which the attendance belongs to
     */
    void deleteAttendancesByLaboratory(int laboratoryId);

    /**
     * Returns all the attendances of the student whose pnc is the same as the method parameter.
     *
     * @param studentPnc pnc of the student
     * @return List<Attendance>
     */
    List<Attendance> getStudentAttendances(String studentPnc);
}
