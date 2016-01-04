package com.iquestint.exception.user;

import com.iquestint.exception.generic.ControllerEntityBindingException;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * This exception is thrown from the rest module a binding on a user cannot be made.
 *
 * @author Georgian Vladutu
 */
public class ControllerUserBindingException extends ControllerEntityBindingException {

    private static final String MESSAGE = "The user has missing fields or the fields types are incorrect";

    public ControllerUserBindingException(List<FieldError> errors) {
        super(errors, MESSAGE);
    }
}
