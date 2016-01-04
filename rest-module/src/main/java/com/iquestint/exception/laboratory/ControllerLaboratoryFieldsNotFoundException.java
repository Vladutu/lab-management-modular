package com.iquestint.exception.laboratory;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * This exception is thrown from the rest module when any of a laboratory fields are not found.
 *
 * @author Georgian Vladutu
 */
public class ControllerLaboratoryFieldsNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "One or more laboratory field were not found";

    public ControllerLaboratoryFieldsNotFoundException() {
        super(MESSAGE);
    }
}
