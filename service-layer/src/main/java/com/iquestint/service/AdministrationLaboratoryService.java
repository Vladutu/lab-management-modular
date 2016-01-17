package com.iquestint.service;

import com.iquestint.dto.LaboratoryDto;
import com.iquestint.dto.SectionDto;
import com.iquestint.dto.SemesterDto;
import com.iquestint.dto.YearDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Laboratory;

import java.util.List;

/**
 * This interfaces provides methods for manipulating Laboratory objects.
 *
 * @author Georgian Vladutu
 */
public interface AdministrationLaboratoryService {

    /**
     * This method saves a laboratory.
     *
     * @param laboratoryDto laboratory to be saved
     * @throws ServiceEntityNotFoundException      if any of it's component fields are not found
     * @throws ServiceEntityAlreadyExistsException if the laboratory already exists in the repository
     */
    void saveLaboratory(LaboratoryDto laboratoryDto)
        throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

    /**
     * This method deletes a laboratory by specifying it's id.
     *
     * @param id the id of the laboratory
     * @throws ServiceEntityNotFoundException if the laboratory is not found
     */
    void deleteLaboratory(int id) throws ServiceEntityNotFoundException;

    /**
     * Returns a laboratory by specifying it's id.
     *
     * @param id the id of the laboratory
     * @return LaboratoryDto
     * @throws ServiceEntityNotFoundException if the laboratory with the specified id is not found
     */
    LaboratoryDto getLaboratoryById(int id) throws ServiceEntityNotFoundException;

    /**
     * Retrieves all laboratories.
     *
     * @return list of laboratories
     */
    List<Laboratory> getAllLaboratories();

    /**
     * Retrieves the laboratories which have the same section, year and semester as the method paramaters.
     *
     * @param sectionDto  SectionDto
     * @param yearDto     YearDto
     * @param semesterDto SemesterDto
     * @return list of laboratories
     */
    List<LaboratoryDto> getLaboratories(SectionDto sectionDto, YearDto yearDto, SemesterDto semesterDto);

    /**
     * This method update a laboratory.
     *
     * @param laboratoryDto laboratory to be updated
     * @throws ServiceEntityNotFoundException if the laboratory is not found
     */
    void updateLaboratory(LaboratoryDto laboratoryDto) throws ServiceEntityNotFoundException;
}
