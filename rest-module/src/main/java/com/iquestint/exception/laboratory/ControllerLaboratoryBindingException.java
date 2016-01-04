package com.iquestint.exception.laboratory;

import com.iquestint.exception.generic.ControllerEntityBindingException;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * This exception is thrown from the rest module when a laboratory binding cannot be made.
 *
 * @author Georgian Vladutu
 */
public class ControllerLaboratoryBindingException extends ControllerEntityBindingException {

    private static final String MESSAGE = "The laboratory has missing fields or the fields types are incorrect";

    public ControllerLaboratoryBindingException(List<FieldError> errors) {
        super(errors, MESSAGE);
    }
}
