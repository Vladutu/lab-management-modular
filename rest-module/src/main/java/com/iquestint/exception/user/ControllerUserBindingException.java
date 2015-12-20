package com.iquestint.exception.user;

import com.iquestint.exception.generic.ControllerEntityBindingException;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author vladu
 */
public class ControllerUserBindingException extends ControllerEntityBindingException {

    private static final String MESSAGE = "The user has missing fields or the fields types are incorrect";

    public ControllerUserBindingException(List<FieldError> errors) {
        super(errors, MESSAGE);
    }
}
