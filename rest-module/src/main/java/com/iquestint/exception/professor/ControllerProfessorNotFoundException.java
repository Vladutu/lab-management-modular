package com.iquestint.exception.professor;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * This exception is thrown from the rest module when a professor is not found.
 *
 * @author Georgian Vladutu
 */
public class ControllerProfessorNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "Professor not found";

    public ControllerProfessorNotFoundException() {
        super(MESSAGE);
    }
}
