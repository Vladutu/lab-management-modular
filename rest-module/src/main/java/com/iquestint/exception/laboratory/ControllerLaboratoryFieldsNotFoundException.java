package com.iquestint.exception.laboratory;

import com.iquestint.exception.generic.ControllerEntityNotFoundException;

/**
 * @author vladu
 */
public class ControllerLaboratoryFieldsNotFoundException extends ControllerEntityNotFoundException {

    private static final String MESSAGE = "One or more laboratory field were not found";

    public ControllerLaboratoryFieldsNotFoundException() {
        super(MESSAGE);
    }
}
