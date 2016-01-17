package com.iquestint.dao.impl;

import com.iquestint.dao.ProfessorDao;
import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Professor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class implements ProfessorDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("professorDao")
public class ProfessorDaoImpl extends JpaDao<Professor> implements ProfessorDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorDaoImpl.class);

    @Override
    public List<Professor> getAllProfessors() {
        return getAll();
    }

    @Override
    public Professor getProfessorByPnc(String pnc) throws DaoEntityNotFoundException {
        TypedQuery<Professor> query = getEntityManager().createQuery("SELECT p FROM Professor p WHERE p.pnc = :pnc",
            Professor.class);
        query.setParameter("pnc", pnc);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.debug("NoResultException");
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public Professor getProfessorByName(String firstName, String lastName) throws DaoEntityNotFoundException {
        TypedQuery<Professor> query = getEntityManager().createQuery(
            "SELECT p FROM Professor p WHERE p.firstName = :fName AND p.lastName = :lName ",
            Professor.class);
        query.setParameter("fName", firstName);
        query.setParameter("lName", lastName);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.debug("NoResultException");
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public void saveProfessor(Professor professor) throws DaoEntityAlreadyExistsException {
        try {
            Professor p = getProfessorByPnc(professor.getPnc());
        } catch (DaoEntityNotFoundException e) {
            persist(professor);
            return;
        }

        throw new DaoEntityAlreadyExistsException();
    }

    @Override
    public void updateProfessor(Professor professor) throws DaoEntityNotFoundException {
        Professor p = getProfessorByPnc(professor.getPnc());
        update(professor);
    }

    @Override
    public void deleteProfessorByPnc(String pnc) throws DaoEntityNotFoundException {
        Professor professor = getProfessorByPnc(pnc);
        delete(professor);
    }
}
