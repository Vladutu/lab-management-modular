package com.iquestint.convereter;

import com.iquestint.model.Professor;
import org.modelmapper.AbstractConverter;

/**
 * @author vladu
 */
public class ProfessorToFormProfessorDtoConverter extends AbstractConverter<Professor, String> {

    @Override
    protected String convert(Professor professor) {
        return professor.getPnc() + "(" + professor.getFirstName() + " " + professor.getLastName() + ")";
    }
}
