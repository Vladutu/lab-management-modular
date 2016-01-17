package com.iquestint.convereter;

import com.iquestint.model.Professor;
import org.modelmapper.AbstractConverter;

/**
 * This class is used by ModelMapper to concatenate Professor fields into one string.
 *
 * @author Georgian Vladutu
 */
public class ProfessorToFormProfessorDtoConverter extends AbstractConverter<Professor, String> {

    @Override
    protected String convert(Professor professor) {
        return professor.getPnc() + "(" + professor.getFirstName() + " " + professor.getLastName() + ")";
    }
}
