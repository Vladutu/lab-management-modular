package com.iquestint.exception.user;

import com.iquestint.exception.generic.ControllerEntityAlreadyExistsException;

/**
 * @author vladu
 */
public class ControllerUserAlreadyExistsException extends ControllerEntityAlreadyExistsException {

    private static final String MESSAGE = "User already exists";

    public ControllerUserAlreadyExistsException() {
        super(MESSAGE);
    }
}
