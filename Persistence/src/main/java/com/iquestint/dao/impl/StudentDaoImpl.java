package com.iquestint.dao.impl;

import com.iquestint.dao.StudentDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class implements StudentDao interfaces;
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
    public Student findStudentByPnc(String pnc) throws DaoEntityNotFoundException {
        TypedQuery<Student> query = getEntityManager().createQuery("SELECT s FROM Student s WHERE s.pnc = :pnc",
            Student.class);
        query.setParameter("pnc", pnc);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
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
        } catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public void saveStudent(Student student) throws DaoEntityAlreadyExists {
        try {
            Student s = findStudentByPnc(student.getPnc());
        } catch (DaoEntityNotFoundException e) {
            persist(student);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void updateStudent(Student student) throws DaoEntityNotFoundException {
        Student s = findStudentByPnc(student.getPnc());
        update(student);
    }

    @Override
    public void deleteStudentByPnc(String pnc) throws DaoEntityNotFoundException {
        Student student = findStudentByPnc(pnc);
        delete(student);
    }

    @Override
    public List<Student> findStudents(Section section, Year year, Semester semester, Group group, Subgroup subgroup) {
        TypedQuery<Student> query = getEntityManager().createQuery(
            "SELECT s FROM Student s WHERE s.section.name = :section AND s.year.value = :year AND s.semester.value = :semester " +
                "AND s.group.name = :groupName AND s.subgroup.name = :subgroup",
            Student.class);
        query.setParameter("section", section.getName());
        query.setParameter("year", year.getValue());
        query.setParameter("semester", semester.getValue());
        query.setParameter("groupName", group.getName());
        query.setParameter("subgroup", subgroup.getName());

        return query.getResultList();
    }

}
