package com.iquestint.dao.implementations;

import com.iquestint.dao.interfaces.LaboratoryDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Laboratory;
import org.springframework.stereotype.Repository;

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
