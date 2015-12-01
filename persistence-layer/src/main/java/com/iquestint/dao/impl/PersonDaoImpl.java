package com.iquestint.dao.impl;

import com.iquestint.dao.PersonDao;
import com.iquestint.dao.ProfessorDao;
import com.iquestint.dao.StudentDao;
import com.iquestint.enums.Type;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Person;
import com.iquestint.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * This class implements the PersonDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository("personDao")
public class PersonDaoImpl extends AbstractDao<Person> implements PersonDao {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProfessorDao professorDao;

    @Override
    public Type getPersonType(String firstName, String lastName) throws DaoEntityNotFoundException {
        TypedQuery<Person> query = getEntityManager().createQuery(
            "SELECT p FROM Person p WHERE p.firstName = :fName AND p.lastName = :lName ",
            Person.class);
        query.setParameter("fName", firstName);
        query.setParameter("lName", lastName);

        try {
            Person person = query.getSingleResult();
        } catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }

        try {
            Student student = studentDao.getStudentByName(firstName, lastName);

            return Type.STUDENT;
        } catch (NoResultException ignored) {
            return Type.PROFESSOR;
        }
    }

    @Override
    public Type getPersonType(String pnc) throws DaoEntityNotFoundException {
        TypedQuery<Person> query = getEntityManager().createQuery("SELECT p FROM Person p WHERE p.pnc = :pnc",
            Person.class);
        query.setParameter("pnc", pnc);

        try {
            Person person = query.getSingleResult();
        } catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }

        try {
            Student student = studentDao.getStudentByPnc(pnc);

            return Type.STUDENT;
        } catch (DaoEntityNotFoundException ignored) {
            return Type.PROFESSOR;
        }
    }

    @Override
    public Person getPersonByPnc(String pnc) throws DaoEntityNotFoundException {
        TypedQuery<Person> query = getEntityManager().createQuery("SELECT p FROM Person p WHERE p.pnc = :pnc",
            Person.class);
        query.setParameter("pnc", pnc);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }
}
