package com.iquestint.service;

import com.iquestint.dto.LaboratoryWithStudentsDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceInvalidSemesterException;

import java.time.LocalDateTime;

/**
 * @author vladu
 */
public interface ProfessorService {

    LaboratoryWithStudentsDto getCurrentLaboratory(String professorPnc, LocalDateTime date)
        throws ServiceInvalidSemesterException, ServiceEntityNotFoundException;
}
