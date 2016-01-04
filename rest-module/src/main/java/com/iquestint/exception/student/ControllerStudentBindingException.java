package com.iquestint.exception.student;

import com.iquestint.exception.generic.ControllerEntityBindingException;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * This exception is thrown from the rest module when binding of a student cannot be made.
 *
 * @author Georgian Vladutu
 */
public class ControllerStudentBindingException extends ControllerEntityBindingException {

    private static final String MESSAGE = "The student has missing fields or the fields types are incorrect";

    public ControllerStudentBindingException(List<FieldError> errors) {
        super(errors, MESSAGE);
    }
}
