package com.iquestint.exception.laboratory;

import com.iquestint.exception.generic.ControllerEntityAlreadyExistsException;

/**
 * This exception is thrown from the rest module when a laboratory already exists.
 *
 * @author Georgian Vladutu
 */
public class ControllerLaboratoryAlreadyExistsException extends ControllerEntityAlreadyExistsException {

    private static final String MESSAGE = "Laboratory already exists";

    public ControllerLaboratoryAlreadyExistsException() {
        super(MESSAGE);
    }
}
