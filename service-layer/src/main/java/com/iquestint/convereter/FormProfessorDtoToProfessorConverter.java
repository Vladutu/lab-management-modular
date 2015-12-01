package com.iquestint.convereter;

import org.modelmapper.AbstractConverter;

/**
 * This class is used by ModelMapper to extract professor's pnc from a concatenated string.
 *
 * @author Georgian Vladutu
 */
public class FormProfessorDtoToProfessorConverter extends AbstractConverter<String, String> {

    @Override
    protected String convert(String s) {
        String newString = s.replaceAll("\\(", " ").replaceAll("\\)", " ");
        String[] tokens = newString.split(" ");

        return tokens[0];
    }
}
