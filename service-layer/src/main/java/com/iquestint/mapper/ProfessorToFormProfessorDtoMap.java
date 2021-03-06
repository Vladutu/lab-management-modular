package com.iquestint.mapper;

import com.iquestint.convereter.ProfessorToFormProfessorDtoConverter;
import com.iquestint.dto.FormProfessorDto;
import com.iquestint.model.Professor;
import org.modelmapper.PropertyMap;

/**
 * This class is used by ModelMapper to map from Professor class to FormProfessorDto class.
 *
 * @author Georgian Vladutu
 */
public class ProfessorToFormProfessorDtoMap extends PropertyMap<Professor, FormProfessorDto> {

    @Override
    protected void configure() {
        map(source.getPnc(), destination.getPnc());
        map(source.getFirstName(), destination.getFirstName());
        map(source.getLastName(), destination.getLastName());
        using(new ProfessorToFormProfessorDtoConverter()).map(source, destination.getCompressedFields());
    }
}
