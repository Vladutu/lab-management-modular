package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao {

    public List<Student> findAllStudents() {
        return getAll();
    }

    public Student findById(int id) throws DaoEntityNotFoundException {
        return getById(id);
    }

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

    public void saveStudent(Student student) throws DaoEntityAlreadyExists {
        try {
            Student s = findStudentByName(student.getFirstName(), student.getLastName());
        }
        catch (DaoEntityNotFoundException e) {
            persist(student);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    public void updateStudent(Student student) {
        update(student);
    }

    public void deleteStudentById(int id) {
        Student student = getEntityManager().find(getPersistentClass(), id);
        delete(student);
    }
}
