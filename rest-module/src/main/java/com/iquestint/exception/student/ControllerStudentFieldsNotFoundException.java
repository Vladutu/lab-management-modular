package com.iquestint.exception.student;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * This exception is thrown from the rest module when any of the student fields are not found.
 *
 * @author Georgian Vladutu
 */
public class ControllerStudentFieldsNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "One or more student field were not found";

    public ControllerStudentFieldsNotFoundException() {
        super(MESSAGE);
    }
}
