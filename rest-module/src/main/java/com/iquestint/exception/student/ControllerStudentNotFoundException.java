package com.iquestint.exception.student;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * @author vladu
 */
public class ControllerStudentNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "Student not found";

    public ControllerStudentNotFoundException() {
        super(MESSAGE);
    }
}
