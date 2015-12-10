package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Attendance;

import java.util.List;

/**
 * @author vladu
 */
public interface AttendanceDao {

    Attendance getAttendanceById(int id) throws DaoEntityNotFoundException;

    void saveAttendance(Attendance attendance) throws DaoEntityAlreadyExists;

    void updateAttendance(Attendance attendance) throws DaoEntityAlreadyExists;

    void deleteAttendancesByStudent(String studentPnc);

    void deleteAttendancesByLaboratory(int laboratoryId);

    List<Attendance> getStudentAttendances(String studentPnc);
}
