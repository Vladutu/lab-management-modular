package com.iquestint.dao.impl;

import com.iquestint.dao.LaboratoryDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class implements LaboratoryDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("laboratoryDao")
public class LaboratoryDaoImpl extends JpaDao<Laboratory> implements LaboratoryDao {

    @Override
    public List<Laboratory> getAllLaboratories() {
        return getAll();
    }

    @Override
    public List<Laboratory> getLaboratories(Section section, Year year, Semester semester) {
        TypedQuery<Laboratory> query = getEntityManager().createQuery(
            "SELECT l FROM Laboratory l WHERE l.section.name = :section AND l.year.value = :year AND l.semester.value = :semester ORDER BY l.name",
            Laboratory.class);
        query.setParameter("section", section.getName());
        query.setParameter("year", year.getValue());
        query.setParameter("semester", semester.getValue());

        return query.getResultList();
    }

    @Override
    public List<Laboratory> getLaboratories(Section section, Year year, Semester semester, Group group,
        Subgroup subgroup) {
        TypedQuery<Laboratory> query = getEntityManager().createQuery(
            "SELECT l FROM Laboratory l WHERE l.section.name = :section AND l.year.value = :year AND l.semester.value = :semester " +
                "AND l.group.name = :groupName AND l.subgroup.name = :subgroup",
            Laboratory.class);
        query.setParameter("section", section.getName());
        query.setParameter("year", year.getValue());
        query.setParameter("semester", semester.getValue());
        query.setParameter("groupName", group.getName());
        query.setParameter("subgroup", subgroup.getName());

        return query.getResultList();
    }

    @Override
    public Laboratory getLaboratoryById(int id) throws DaoEntityNotFoundException {
        return getById(id);
    }

    @Override
    public void saveLaboratory(Laboratory laboratory) throws DaoEntityAlreadyExists {
        try {
            Laboratory l = getLaboratoryById(laboratory.getId());
        } catch (DaoEntityNotFoundException e) {
            persist(laboratory);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void updateLaboratory(Laboratory laboratory) throws DaoEntityNotFoundException {
        Laboratory l = getLaboratoryById(laboratory.getId());
        update(laboratory);
    }

    @Override
    public void deleteLaboratoryById(int id) throws DaoEntityNotFoundException {
        Laboratory laboratory = getLaboratoryById(id);
        delete(laboratory);
    }

    @Override
    public List<Laboratory> getLaboratoriesByDateAndTime(String professorPnc, Hour from, Day day) {
        TypedQuery<Laboratory> query = getEntityManager().createQuery(
            "SELECT l FROM Laboratory l WHERE l.from.value = :fromH and l.day.value = :day and l.professor.pnc = :pnc",
            Laboratory.class);
        query.setParameter("fromH", from.getValue());
        query.setParameter("day", day.getValue());
        query.setParameter("pnc", professorPnc);

        return query.getResultList();
    }

    @Override
    public List<Laboratory> getLaboratoriesByProfessor(String professorPnc) {
        TypedQuery<Laboratory> query = getEntityManager().createQuery(
            "SELECT l FROM Laboratory l WHERE l.professor.pnc = :pnc",
            Laboratory.class);

        query.setParameter("pnc", professorPnc);

        return query.getResultList();
    }
}
