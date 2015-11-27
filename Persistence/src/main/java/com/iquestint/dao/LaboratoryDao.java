package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.*;

import java.util.List;

/**
 * @author vladu
 */
public interface LaboratoryDao {

    List<Laboratory> getAllLaboratories();

    List<Laboratory> getLaboratories(Section section, Year year, Semester semester);

    List<Laboratory> getLaboratories(Section section, Year year, Semester semester, Group group, Subgroup subgroup);

    Laboratory getLaboratoryById(int id) throws DaoEntityNotFoundException;

    void saveLaboratory(Laboratory laboratory) throws DaoEntityAlreadyExists;

    void updateLaboratory(Laboratory laboratory) throws DaoEntityNotFoundException;

    void deleteLaboratoryById(int id) throws DaoEntityNotFoundException;
}
