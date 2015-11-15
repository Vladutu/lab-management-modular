package com.iquestint.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is a validator for @Email annotation.
 *
 * @author Georgian Vladutu
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    private static String REGEX =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void initialize(Email email) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.matches(REGEX)) {
            return true;
        }

        return false;
    }
}
