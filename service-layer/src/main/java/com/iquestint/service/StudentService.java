package com.iquestint.service;

import com.iquestint.dto.GradeDto;
import com.iquestint.dto.LaboratoryDto;

import java.util.List;

/**
 * @author vladu
 */
public interface StudentService {

    /**
     * Returns all laboratories of the student whose pnc is studentPnc.
     *
     * @param studentPnc the pnc of the student
     * @return list of laboratories
     */
    List<LaboratoryDto> getStudentLaboratories(String studentPnc);

    /**
     * Returns the grades of the student whose pnc is studentPnc from the laboratory whose id is laboratoryId which took place on date date.
     *
     * @param studentPnc   pnc of the student
     * @param laboratoryId id of the laboratory
     * @return list of grades
     */
    List<GradeDto> getStudentGradesByLaboratory(String studentPnc, int laboratoryId);
}
