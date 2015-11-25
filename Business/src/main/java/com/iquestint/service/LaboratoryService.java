package com.iquestint.service;

import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Section;
import com.iquestint.model.Semester;
import com.iquestint.model.Year;

import java.util.List;

/**
 * @author vladu
 */
public interface LaboratoryService {

    void saveLaboratory(Laboratory laboratory)
        throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

    void deleteLaboratory(int id) throws ServiceEntityNotFoundException;

    Laboratory getLaboratoryById(int id) throws ServiceEntityNotFoundException;

    List<Laboratory> getAllLaboratories();

    List<Laboratory> getLaboratories(Section section, Year year, Semester semester);

    void updateLaboratory(Laboratory laboratory) throws ServiceEntityNotFoundException;
}
