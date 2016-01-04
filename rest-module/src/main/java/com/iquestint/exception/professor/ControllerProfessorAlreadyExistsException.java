package com.iquestint.exception.professor;

import com.iquestint.exception.generic.ControllerEntityAlreadyExistsException;

/**
 * This exception is thrown from the rest module when a professor already exists.
 *
 * @author Georgian Vladutu
 */
public class ControllerProfessorAlreadyExistsException extends ControllerEntityAlreadyExistsException {

    private static final String MESSAGE = "Professor already exists";

    public ControllerProfessorAlreadyExistsException() {
        super(MESSAGE);
    }
}
