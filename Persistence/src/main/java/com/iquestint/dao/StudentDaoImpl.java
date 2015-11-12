package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class implements StudentDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao {

    @Override
    public List<Student> findAllStudents() {
        return getAll();
    }

    @Override
    public Student findByPnc(String pnc) throws DaoEntityNotFoundException {
        TypedQuery<Student> query = getEntityManager().createQuery("SELECT s FROM Student s WHERE s.pnc = :pnc",
            Student.class);
        query.setParameter("pnc", pnc);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public Student findStudentByName(String firstName, String lastName) throws DaoEntityNotFoundException {
        TypedQuery<Student> query = getEntityManager().createQuery(
            "SELECT s FROM Student s WHERE s.firstName = :fName AND s.lastName = :lName ",
            Student.class);
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
    public void saveStudent(Student student) throws DaoEntityAlreadyExists {
        try {
            Student s = findByPnc(student.getPnc());
        }
        catch (DaoEntityNotFoundException e) {
            persist(student);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void updateStudent(Student student) throws DaoEntityNotFoundException {
        try {
            Student s = findByPnc(student.getPnc());
            update(student);
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public void deleteStudentByPnc(String pnc) throws DaoEntityNotFoundException {
        try {
            Student student = findByPnc(pnc);
            delete(student);
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

}
