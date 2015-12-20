package com.iquestint.exception.laboratory;

import com.iquestint.exception.generic.ControllerEntityAlreadyExistsException;

/**
 * @author vladu
 */
public class ControllerLaboratoryAlreadyExistsException extends ControllerEntityAlreadyExistsException {

    private static final String MESSAGE = "Laboratory already exists";

    public ControllerLaboratoryAlreadyExistsException() {
        super(MESSAGE);
    }
}
