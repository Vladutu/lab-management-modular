package com.iquestint.validation;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.service.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is a validator for the @PersonExists annotation.
 *
 * @author Georgian Vladutu
 */
@Component
public class PersonValidator implements ConstraintValidator<PersonExists, String> {

    @Autowired
    private PersonService personService;

    @Override
    public void initialize(PersonExists personExists) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            personService.getPersonType(s);

            return true;
        }
        catch (ServiceEntityNotFoundException e) {
            return false;
        }
    }
}
