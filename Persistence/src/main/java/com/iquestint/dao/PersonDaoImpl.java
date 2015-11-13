package com.iquestint.dao;

import com.iquestint.enums.Type;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Person;
import com.iquestint.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * @author vladu
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
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }

        try {
            Student student = studentDao.findStudentByName(firstName, lastName);

            return Type.STUDENT;
        }
        catch (NoResultException ignored) {
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
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }

        try {
            Student student = studentDao.findByPnc(pnc);

            return Type.STUDENT;
        }
        catch (NoResultException | DaoEntityNotFoundException ignored) {
            return Type.PROFESSOR;
        }
    }
}
