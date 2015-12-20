package com.iquestint.exception.professor;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * @author vladu
 */
public class ControllerProfessorNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "Professor not found";

    public ControllerProfessorNotFoundException() {
        super(MESSAGE);
    }
}
