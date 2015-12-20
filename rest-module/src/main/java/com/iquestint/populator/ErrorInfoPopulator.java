package com.iquestint.populator;

import com.iquestint.error.BindingErrorInfo;
import com.iquestint.error.FieldErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vladu
 */
@Component
public class ErrorInfoPopulator {

    public BindingErrorInfo populate(List<FieldError> errors, String message, HttpStatus status) {
        List<FieldErrorInfo> fieldErrors = new ArrayList<>();
        for (FieldError error : errors) {
            FieldErrorInfo fieldError = new FieldErrorInfo(error.getField(), error.getDefaultMessage());
            fieldErrors.add(fieldError);
        }

        return new BindingErrorInfo(status.value(), message, fieldErrors);
    }
}
