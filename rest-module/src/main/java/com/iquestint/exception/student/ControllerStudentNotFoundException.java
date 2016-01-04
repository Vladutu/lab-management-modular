package com.iquestint.exception.student;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * This exception is thrown from the rest module when a student is not found.
 *
 * @author Georgian Vladutu
 */
public class ControllerStudentNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "Student not found";

    public ControllerStudentNotFoundException() {
        super(MESSAGE);
    }
}
