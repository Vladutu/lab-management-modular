package com.iquestint.mapper;

import com.iquestint.convereter.FormProfessorDtoToProfessorConverter;
import com.iquestint.dto.FormProfessorDto;
import com.iquestint.model.Professor;
import org.modelmapper.PropertyMap;

/**
 * @author vladu
 */
public class FormProfessorDtoToProfessorMap extends PropertyMap<FormProfessorDto, Professor> {

    @Override
    protected void configure() {
        map(source.getLastName(), destination.getLastName());
        map(source.getFirstName(), destination.getFirstName());
        using(new FormProfessorDtoToProfessorConverter()).map(source.getCompressedFields(), destination.getPnc());
    }
}
