package com.iquestint.validation;

import com.iquestint.dao.YearDao;
import com.iquestint.model.Year;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class is a validator for @YearExists annotation.
 *
 * @author Georgian Vladutu
 */
public class YearValidator implements ConstraintValidator<YearExists, Integer> {

    @Autowired
    private YearDao yearDao;

    @Override
    public void initialize(YearExists yearExists) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        List<Year> years = yearDao.getAllYears();

        for (Year year : years) {
            if (year.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
