package com.iquestint.convereter;

import org.modelmapper.AbstractConverter;

/**
 * @author vladu
 */
public class FormProfessorDtoToProfessorConverter extends AbstractConverter<String, String> {

    @Override
    protected String convert(String s) {
        String newString = s.replaceAll("\\(", " ").replaceAll("\\)", " ");
        String[] tokens = newString.split(" ");

        return tokens[0];
    }
}
