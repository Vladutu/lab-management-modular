package com.iquestint.service;

import com.iquestint.dto.SectionDto;
import com.iquestint.dto.SemesterDto;
import com.iquestint.dto.YearDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Laboratory;
import com.iquestint.dto.LaboratoryDto;

import java.util.List;

/**
 * @author vladu
 */
public interface LaboratoryService {

    void saveLaboratory(LaboratoryDto laboratoryDto)
        throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

    void deleteLaboratory(int id) throws ServiceEntityNotFoundException;

    LaboratoryDto getLaboratoryById(int id) throws ServiceEntityNotFoundException;

    List<Laboratory> getAllLaboratories();

    List<LaboratoryDto> getLaboratories(SectionDto sectionDto, YearDto yearDto, SemesterDto semesterDto);

    void updateLaboratory(LaboratoryDto laboratoryDto) throws ServiceEntityNotFoundException;
}
