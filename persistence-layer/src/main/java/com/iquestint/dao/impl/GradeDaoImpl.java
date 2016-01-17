package com.iquestint.dao.impl;

import com.iquestint.dao.GradeDao;
import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

/**
 * This class implements GradeDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("gradeDao")
public class GradeDaoImpl extends JpaDao<Grade> implements GradeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(GradeDaoImpl.class);

    @Override
    public Grade getGradeById(int id) throws DaoEntityNotFoundException {
        return getById(id);
    }

    @Override
    public void saveGrade(Grade grade) throws DaoEntityAlreadyExistsException {
        try {
            Grade g = getGradeById(grade.getId());
        } catch (DaoEntityNotFoundException e) {
            persist(grade);
            return;
        }

        throw new DaoEntityAlreadyExistsException();
    }

    @Override
    public void updateGrade(Grade grade) throws DaoEntityNotFoundException {
        Grade g = getGradeById(grade.getId());
        update(grade);
    }

    @Override
    public void deleteGradesByStudent(String studentPnc) {
        Query query = getEntityManager().createQuery("DELETE  FROM Grade g WHERE g.student.pnc = :pnc");
        query.setParameter("pnc", studentPnc);

        query.executeUpdate();
    }

    @Override
    public void deleteGradesByLaboratory(int laboratoryId) {
        Query query = getEntityManager().createQuery("DELETE  FROM Grade g WHERE g.laboratory.id = :id");
        query.setParameter("id", laboratoryId);

        query.executeUpdate();
    }

    @Override
    public List<Grade> getStudentGradesByLaboratory(String studentPnc, int laboratoryId) {
        TypedQuery<Grade> query = getEntityManager().createQuery(
            "SELECT g FROM Grade g WHERE g.student.pnc = :pnc AND g.laboratory.id = :id",
            Grade.class);
        query.setParameter("pnc", studentPnc);
        query.setParameter("id", laboratoryId);

        return query.getResultList();
    }

    @Override
    public Grade getStudentGrade(String studentPnc, int laboratoryId, LocalDate date)
        throws DaoEntityNotFoundException {
        TypedQuery<Grade> query = getEntityManager().createQuery(
            "SELECT g FROM Grade g WHERE g.student.pnc = :pnc AND g.laboratory.id = :id AND g.date = :date",
            Grade.class);
        query.setParameter("pnc", studentPnc);
        query.setParameter("id", laboratoryId);
        query.setParameter("date", date);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.debug("NoResultException");
            throw new DaoEntityNotFoundException();
        }
    }
}
