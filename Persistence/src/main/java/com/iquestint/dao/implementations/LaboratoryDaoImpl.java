package com.iquestint.dao.implementations;

import com.iquestint.dao.interfaces.LaboratoryDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Section;
import com.iquestint.model.Semester;
import com.iquestint.model.Year;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
@Repository("laboratoryDao")
public class LaboratoryDaoImpl extends AbstractDao<Laboratory> implements LaboratoryDao {

    @Override
    public List<Laboratory> findAllLaboratories() {
        return getAll();
    }

    @Override
    public List<Laboratory> findLaboratories(Section section, Year year, Semester semester) {
        TypedQuery<Laboratory> query = getEntityManager().createQuery(
            "SELECT l FROM Laboratory l WHERE l.section.name = :section AND l.year.value = :year AND l.semester.value = :semester ORDER BY " +
                "l.group.name, l.subgroup.name,l.day.value,l.from.value",
            Laboratory.class);
        query.setParameter("section", section.getName());
        query.setParameter("year", year.getValue());
        query.setParameter("semester", semester.getValue());

        return query.getResultList();
    }

    @Override
    public Laboratory findLaboratoryById(int id) throws DaoEntityNotFoundException {
        return getById(id);
    }

    @Override
    public void saveLaboratory(Laboratory laboratory) throws DaoEntityAlreadyExists {
        try {
            Laboratory l = findLaboratoryById(laboratory.getId());
        }
        catch (DaoEntityNotFoundException e) {
            persist(laboratory);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void updateLaboratory(Laboratory laboratory) throws DaoEntityNotFoundException {
        Laboratory l = findLaboratoryById(laboratory.getId());
        update(laboratory);
    }

    @Override
    public void deleteLaboratoryById(int id) throws DaoEntityNotFoundException {
        Laboratory laboratory = findLaboratoryById(id);
        delete(laboratory);
    }
}
