package com.iquestint.exception.user;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * This exception is thrown from the rest module when any of a user fields are not found.
 *
 * @author Georgian Vladutu
 */
public class ControllerUserFieldsNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "One or more user field were not found";

    public ControllerUserFieldsNotFoundException() {
        super(MESSAGE);
    }
}
