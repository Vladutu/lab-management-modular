package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
 */
@Getter
@Setter
public class FormProfessorDto {

    private String pnc;

    private String firstName;

    private String lastName;

    @Override
    public String toString() {
        return pnc + " (" + firstName + " " + lastName + ")";
    }
}
