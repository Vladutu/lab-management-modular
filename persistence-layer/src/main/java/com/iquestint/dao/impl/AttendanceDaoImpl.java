package com.iquestint.dao.impl;

import com.iquestint.dao.AttendanceDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Attendance;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
@Repository("attendanceDao")
public class AttendanceDaoImpl extends JpaDao<Attendance> implements AttendanceDao {

    @Override
    public Attendance getAttendanceById(int id) throws DaoEntityNotFoundException {
        return getById(id);
    }

    @Override
    public void saveAttendance(Attendance attendance) throws DaoEntityAlreadyExists {
        try {
            Attendance a = getAttendanceById(attendance.getId());
        } catch (DaoEntityNotFoundException e) {
            persist(attendance);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void updateAttendance(Attendance attendance) throws DaoEntityAlreadyExists {
        try {
            Attendance a = getAttendanceById(attendance.getId());
        } catch (DaoEntityNotFoundException e) {
            update(attendance);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void deleteAttendancesByStudent(String studentPnc) {
        Query query = getEntityManager().createQuery("DELETE  FROM Attendance a WHERE a.student.pnc = :pnc");
        query.setParameter("pnc", studentPnc);

        query.executeUpdate();
    }

    @Override
    public void deleteAttendancesByLaboratory(int laboratoryId) {
        Query query = getEntityManager().createQuery("DELETE  FROM Attendance a WHERE a.laboratory.id = :id");
        query.setParameter("id", laboratoryId);

        query.executeUpdate();
    }

    @Override
    public List<Attendance> getStudentAttendances(String studentPnc) {
        TypedQuery<Attendance> query = getEntityManager().createQuery(
            "SELECT a FROM Attendance a WHERE a.student.pnc = :pnc", Attendance.class);
        query.setParameter("pnc", studentPnc);

        return query.getResultList();
    }
}
