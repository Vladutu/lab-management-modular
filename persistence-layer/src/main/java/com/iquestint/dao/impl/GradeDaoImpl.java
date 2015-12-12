package com.iquestint.dao.impl;

import com.iquestint.dao.GradeDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Grade;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class implements GradeDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("gradeDao")
public class GradeDaoImpl extends JpaDao<Grade> implements GradeDao {

    @Override
    public Grade getGradeById(int id) throws DaoEntityNotFoundException {
        return getById(id);
    }

    @Override
    public void saveGrade(Grade grade) throws DaoEntityAlreadyExists {
        try {
            Grade g = getGradeById(grade.getId());
        } catch (DaoEntityNotFoundException e) {
            persist(grade);
            return;
        }

        throw new DaoEntityAlreadyExists();
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
    public List<Grade> getStudentGrades(String studentPnc) {
        TypedQuery<Grade> query = getEntityManager().createQuery("SELECT g FROM Grade g WHERE g.student.pnc = :pnc",
            Grade.class);
        query.setParameter("pnc", studentPnc);

        return query.getResultList();
    }
}
