package com.iquestint.exception.professor;

import com.iquestint.exception.generic.ControllerEntityAlreadyExistsException;

/**
 * @author vladu
 */
public class ControllerProfessorAlreadyExistsException extends ControllerEntityAlreadyExistsException {

    private static final String MESSAGE = "Professor already exists";

    public ControllerProfessorAlreadyExistsException() {
        super(MESSAGE);
    }
}
