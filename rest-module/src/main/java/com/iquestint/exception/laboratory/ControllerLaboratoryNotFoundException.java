package com.iquestint.exception.laboratory;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * This exception is thrown from the rest module when a laboratory is not found.
 *
 * @author Georgian Vladutu
 */
public class ControllerLaboratoryNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "Laboratory not found";

    public ControllerLaboratoryNotFoundException() {
        super(MESSAGE);
    }
}
