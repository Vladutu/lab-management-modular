package com.iquestint.exception.student;

import com.iquestint.exception.generic.ControllerEntityAlreadyExistsException;

/**
 * This exception is thrown from the rest module when a student already exists.
 *
 * @author Georgian Vladutu
 */
public class ControllerStudentAlreadyExistsException extends ControllerEntityAlreadyExistsException {

    private static final String MESSAGE = "Student already exists";

    public ControllerStudentAlreadyExistsException() {
        super(MESSAGE);
    }
}
