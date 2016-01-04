package com.iquestint.exception.user;

import com.iquestint.exception.generic.ControllerEntityAlreadyExistsException;

/**
 * This exception is thrown from the rest module when a user already exists.
 *
 * @author Georgian Vladutu
 */
public class ControllerUserAlreadyExistsException extends ControllerEntityAlreadyExistsException {

    private static final String MESSAGE = "User already exists";

    public ControllerUserAlreadyExistsException() {
        super(MESSAGE);
    }
}
