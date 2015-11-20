package com.iquestint.dao.interfaces;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Section;
import com.iquestint.model.Semester;
import com.iquestint.model.Year;

import java.util.List;

/**
 * @author vladu
 */
public interface LaboratoryDao {

    List<Laboratory> findAllLaboratories();

    List<Laboratory> findLaboratories(Section section, Year year, Semester semester);

    Laboratory findLaboratoryById(int id) throws DaoEntityNotFoundException;

    void saveLaboratory(Laboratory laboratory) throws DaoEntityAlreadyExists;

    void updateLaboratory(Laboratory laboratory) throws DaoEntityNotFoundException;

    void deleteLaboratoryById(int id) throws DaoEntityNotFoundException;
}
