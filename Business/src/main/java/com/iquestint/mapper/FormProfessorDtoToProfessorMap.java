package com.iquestint.mapper;

import com.iquestint.dto.FormProfessorDto;
import com.iquestint.model.Professor;
import org.modelmapper.PropertyMap;

/**
 * @author vladu
 */
public class FormProfessorDtoToProfessorMap extends PropertyMap<FormProfessorDto, Professor> {

    @Override
    protected void configure() {
        map(source.getPnc(), destination.getPnc());
        map(source.getFirstName(), destination.getFirstName());
        map(source.getLastName(), destination.getLastName());
    }
}
