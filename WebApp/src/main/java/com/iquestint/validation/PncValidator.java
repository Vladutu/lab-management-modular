package com.iquestint.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is a validator for the @Pnc annotation.
 *
 * @author Georgian Vladutu
 */
public class PncValidator implements ConstraintValidator<Pnc, String> {

    private static final int PNC_LENGTH = 13;

    @Override
    public void initialize(Pnc pnc) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b";

        return !(!value.matches(regex) || value.length() != PNC_LENGTH);
    }
}
