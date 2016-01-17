package com.iquestint.dao.impl;

import com.iquestint.dao.StudentDao;
import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class StudentDaoImpl extends JpaDao<Student> implements StudentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDaoImpl.class);

    @Override
    public List<Student> getAllStudents() {
        return getAll();
    }

    @Override
    public Student getStudentByPnc(String pnc) throws DaoEntityNotFoundException {
        TypedQuery<Student> query = getEntityManager().createQuery("SELECT s FROM Student s WHERE s.pnc = :pnc",
            Student.class);
        query.setParameter("pnc", pnc);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.debug("NoResultException");
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public Student getStudentByName(String firstName, String lastName) throws DaoEntityNotFoundException {
        TypedQuery<Student> query = getEntityManager().createQuery(
            "SELECT s FROM Student s WHERE s.firstName = :fName AND s.lastName = :lName ",
            Student.class);
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
    public void saveStudent(Student student) throws DaoEntityAlreadyExistsException {
        try {
            Student s = getStudentByPnc(student.getPnc());
        } catch (DaoEntityNotFoundException e) {
            persist(student);
            return;
        }

        throw new DaoEntityAlreadyExistsException();
    }

    @Override
    public void updateStudent(Student student) throws DaoEntityNotFoundException {
        Student s = getStudentByPnc(student.getPnc());
        update(student);
    }

    @Override
    public void deleteStudentByPnc(String pnc) throws DaoEntityNotFoundException {
        Student student = getStudentByPnc(pnc);
        delete(student);
    }

    @Override
    public List<Student> getStudents(Section section, Year year, Semester semester, Group group, Subgroup subgroup) {
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

    @Override
    public List<Student> getStudents(Laboratory laboratory) {
        TypedQuery<Student> query = getEntityManager().createQuery(
            "SELECT s FROM Student s INNER JOIN s.laboratories lab WHERE lab.id IN :id",
            Student.class);
        query.setParameter("id", laboratory.getId());

        return query.getResultList();
    }
}
