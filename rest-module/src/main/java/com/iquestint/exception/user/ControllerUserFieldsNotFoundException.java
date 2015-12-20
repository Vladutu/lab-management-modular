package com.iquestint.exception.user;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * @author vladu
 */
public class ControllerUserFieldsNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "One or more user field were not found";

    public ControllerUserFieldsNotFoundException() {
        super(MESSAGE);
    }
}
