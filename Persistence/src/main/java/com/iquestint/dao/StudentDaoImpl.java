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

    @Override
    public List<Student> findAllStudents() {
        return getAll();
    }

    @Override
    public Student findById(int id) throws DaoEntityNotFoundException {
        Student student = getById(id);

        if (student == null) {
            throw new DaoEntityNotFoundException();
        }

        return student;
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
            Student s = findStudentByName(student.getFirstName(), student.getLastName());
        }
        catch (DaoEntityNotFoundException e) {
            persist(student);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void updateStudent(Student student) throws DaoEntityNotFoundException {
        Student s = findById(student.getId());

        update(student);
    }

    @Override
    public void deleteStudentById(int id) throws DaoEntityNotFoundException {
        Student student = findById(id);

        delete(student);
    }
}
