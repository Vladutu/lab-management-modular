package com.iquestint.exception.student;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * @author vladu
 */
public class ControllerStudentFieldsNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "One or more student field were not found";

    public ControllerStudentFieldsNotFoundException() {
        super(MESSAGE);
    }
}
