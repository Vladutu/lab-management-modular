package com.iquestint.exception.generic;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * This exception is thrown from the rest module when a binding on an entity cannot be made.
 *
 * @author Georgian Vladutu
 */
public class ControllerEntityBindingException extends ControllerException {

    private List<FieldError> errors;

    public ControllerEntityBindingException(List<FieldError> errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
