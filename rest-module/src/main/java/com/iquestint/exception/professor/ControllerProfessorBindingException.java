package com.iquestint.exception.professor;

import com.iquestint.exception.generic.ControllerEntityBindingException;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * This exception is thrown from the rest module when a binding on a professor cannot be made.
 *
 * @author Georgian Vladutu
 */
public class ControllerProfessorBindingException extends ControllerEntityBindingException {

    private static final String MESSAGE = "The professor has missing fields or the fields types are incorrect";

    public ControllerProfessorBindingException(List<FieldError> errors) {
        super(errors, MESSAGE);
    }
}
