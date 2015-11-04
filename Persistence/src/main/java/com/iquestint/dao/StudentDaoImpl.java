package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Student;
import org.springframework.stereotype.Repository;

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

    public void saveStudent(Student student) {
        persist(student);
    }

    public void updateStudent(Student student) {
        update(student);
    }

    public void deleteStudentById(int id) {
        Student student = getEntityManager().find(persistentClass, id);
        delete(student);
    }
}
