package com.iquestint.exception.user;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * @author vladu
 */
public class ControllerUserNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "User not found";

    public ControllerUserNotFoundException() {
        super(MESSAGE);
    }
}
