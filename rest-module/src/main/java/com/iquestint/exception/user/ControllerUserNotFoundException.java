package com.iquestint.exception.user;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * This exception is thrown from the rest module when a user is not found.
 *
 * @author Georgian Vladutu
 */
public class ControllerUserNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "User not found";

    public ControllerUserNotFoundException() {
        super(MESSAGE);
    }
}
