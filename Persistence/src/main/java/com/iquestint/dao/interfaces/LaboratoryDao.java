package com.iquestint.dao.interfaces;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Laboratory;

import java.util.List;

/**
 * @author vladu
 */
public interface LaboratoryDao {

    List<Laboratory> findAllLaboratories();

    Laboratory findLaboratoryById(int id) throws DaoEntityNotFoundException;

    void saveLaboratory(Laboratory laboratory) throws DaoEntityNotFoundException, DaoEntityAlreadyExists;

    void updateLaboratory(Laboratory laboratory) throws DaoEntityNotFoundException;

    void deleteLaboratoryById(int id) throws DaoEntityNotFoundException;
}
