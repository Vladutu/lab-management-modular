package com.iquestint.service;

import com.iquestint.dto.FormLaboratoryCreateDto;
import com.iquestint.dto.FormLaboratoryShowDto;
import com.iquestint.dto.FormStudentDto;
import com.iquestint.dto.FormUserDto;

/**
 * @author vladu
 */
public interface AdministrationFormService {

    FormStudentDto getFormStudent();

    FormUserDto getFormUser();

    FormLaboratoryShowDto getFormLaboratoryShowDto();

    FormLaboratoryCreateDto getFormLaboratoryCreateDto();
}
