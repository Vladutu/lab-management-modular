package com.iquestint.validation;

import com.iquestint.dao.PersonDao;
import com.iquestint.exception.DaoEntityNotFoundException;
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
    private PersonDao personDao;

    @Override
    public void initialize(PersonExists personExists) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            personDao.getPersonType(s);

            return true;
        } catch (DaoEntityNotFoundException e) {
            return false;
        }
    }
}
