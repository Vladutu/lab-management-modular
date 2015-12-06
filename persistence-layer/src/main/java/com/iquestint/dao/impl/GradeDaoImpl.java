package com.iquestint.dao.impl;

import com.iquestint.dao.GradeDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Grade;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
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
    public List<Grade> getStudentGrades(String studentPnc) {
        TypedQuery<Grade> query = getEntityManager().createQuery("SELECT g FROM Grade g WHERE g.student.pnc = :pnc",
            Grade.class);
        query.setParameter("pnc", studentPnc);

        return query.getResultList();
    }
}
