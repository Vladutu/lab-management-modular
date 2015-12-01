package com.iquestint.service;

import com.iquestint.dto.FormLaboratoryCreateDto;
import com.iquestint.dto.FormLaboratoryShowDto;
import com.iquestint.dto.FormStudentDto;
import com.iquestint.dto.FormUserDto;

/**
 * This interface provides methods for constructing data transfer objects by invoking dao methods.
 *
 * @author Georgian Vladutu
 */
public interface AdministrationFormService {

    /**
     * Returns a FromStudentDto which fields are populated from the repository.
     *
     * @return FormStudentDto
     */
    FormStudentDto getFormStudent();

    /**
     * Returns a FromUserDto which fields are populated from the repository.
     *
     * @return FormUserDto
     */
    FormUserDto getFormUser();

    /**
     * Returns a FormLaboratoryShowDto which fields are populated from the repository.
     *
     * @return FormLaboratoryShowDto
     */
    FormLaboratoryShowDto getFormLaboratoryShowDto();

    /**
     * Returns a FormLaboratoryCreateDto which fields are populated from the repository.
     *
     * @return FormLaboratoryCreateDto
     */
    FormLaboratoryCreateDto getFormLaboratoryCreateDto();
}
