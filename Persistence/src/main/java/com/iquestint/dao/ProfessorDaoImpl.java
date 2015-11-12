package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Professor;
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
public class ProfessorDaoImpl extends AbstractDao<Professor> implements ProfessorDao {

    @Override
    public List<Professor> findAllProfessors() {
        return getAll();
    }

    @Override
    public Professor findByPnc(String pnc) throws DaoEntityNotFoundException {
        TypedQuery<Professor> query = getEntityManager().createQuery("SELECT p FROM Professor p WHERE p.pnc = :pnc",
            Professor.class);
        query.setParameter("pnc", pnc);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public Professor findProfessorByName(String firstName, String lastName) throws DaoEntityNotFoundException {
        TypedQuery<Professor> query = getEntityManager().createQuery(
            "SELECT p FROM Professor p WHERE p.firstName = :fName AND p.lastName = :lName ",
            Professor.class);
        query.setParameter("fName", firstName);
        query.setParameter("lName", lastName);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public void saveProfessor(Professor professor) throws DaoEntityAlreadyExists {
        try {
            Professor p = findByPnc(professor.getPnc());
        }
        catch (DaoEntityNotFoundException e) {
            persist(professor);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void updateProfessor(Professor professor) throws DaoEntityNotFoundException {
        try {
            Professor p = findByPnc(professor.getPnc());
            update(professor);
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public void deleteProfessorByPnc(String pnc) throws DaoEntityNotFoundException {
        try {
            Professor professor = findByPnc(pnc);
            delete(professor);
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }
}
