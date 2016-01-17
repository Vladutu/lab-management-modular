package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class FormProfessorDto {

    private String pnc;

    private String firstName;

    private String lastName;

    private String compressedFields;

    @Override
    public String toString() {
        return compressedFields;
    }
}
