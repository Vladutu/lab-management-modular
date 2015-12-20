package com.iquestint.exception.student;

import com.iquestint.exception.generic.ControllerEntityAlreadyExistsException;

/**
 * @author vladu
 */
public class ControllerStudentAlreadyExistsException extends ControllerEntityAlreadyExistsException {

    private static final String MESSAGE = "Student already exists";

    public ControllerStudentAlreadyExistsException() {
        super(MESSAGE);
    }
}
