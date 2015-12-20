package com.iquestint.exception.laboratory;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * @author vladu
 */
public class ControllerLaboratoryNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "Laboratory not found";

    public ControllerLaboratoryNotFoundException() {
        super(MESSAGE);
    }
}
